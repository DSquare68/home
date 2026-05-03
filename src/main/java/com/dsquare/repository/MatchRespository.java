package com.dsquare.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dsquare.db.MatchRecord;

import jakarta.transaction.Transactional;

@Repository
public interface MatchRespository extends JpaRepository<MatchRecord, Integer> {

	@Query(value="SELECT * FROM ADMIN.MATCHES m WHERE m.season = ?1  and m.cup= ?2 and m.mode_of_data LIKE %?3% order by m.queue, m.ID asc",nativeQuery = true)
	ArrayList<MatchRecord> findByCupAndSeason(String season,String cup,String webMode);

	@Query(value="SELECT * FROM ADMIN.MATCHES m WHERE m.mode_of_data != ?1",nativeQuery = true)
	ArrayList<MatchRecord> getByNotMode(String webMode);

	//@Query("UPDATE MatchRecord m SET m = ?2 WHERE m.id = ?1")
	//void update(int id, MatchRecord toUpdate);

	@Query(value="select * from ADMIN.MATCHES m where m.season = ?1  and m.mode_of_data like %?3% and m.queue = ?2",nativeQuery = true)
	List<MatchRecord> findQueueBySeason(String season, int queue , String webMode);

	@Query(value="SELECT m.queue FROM ADMIN.MATCHES m WHERE m.date_of_match > ?1 and m.mode_of_data LIKE %?2% order by m.date_of_match FETCH FIRST 1 ROW ONLY",nativeQuery = true)
	Integer findQueueByDate(Date day, String webMode);

	@Procedure(procedureName = "UPDATE_LAST_QUEUE")
	@Transactional
	void updateLastQueue(@Param("QUEUE_DATA") Integer QUEUE_DATA,@Param("SEASON_DATA") String SEASON_DATA);
	
	@Procedure(procedureName = "CHECK_PREDICTIONS")
	@Transactional
	void checkPredictionQueue(@Param("SEASON_DATA") String SEASON_DATA,@Param("QUEUE_DATA") Integer QUEUE_DATA);

	@Query(value="SELECT DISTINCT m.season FROM ADMIN.MATCHES m ORDER BY m.season DESC",nativeQuery = true)
	String[] findAllSeasons();
	
	@Query(value="DELETE FROM ADMIN.MATCHES m WHERE m.mode_of_data = ?1",nativeQuery = true)
	void deleteAllWhereMode(String webMode);

	@Query(value="SELECT DISTINCT m.cup FROM ADMIN.MATCHES m WHERE m.mode_of_data LIKE %?1% ORDER BY m.cup",nativeQuery = true)
	String[] findCupsDistinct(String webMode);

	@Query(value="SELECT DISTINCT m.queue FROM ADMIN.MATCHES m WHERE m.cup = ?1 and m.mode_of_data LIKE %?2% ORDER BY m.queue",nativeQuery = true)
	Integer[] findQueueOfCup(String cup, String webMode);

}
