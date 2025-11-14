package com.dsquare.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dsquare.db.MatchRecord;

import jakarta.transaction.Transactional;

@Repository
public interface MatchRespository extends JpaRepository<MatchRecord, Integer> {

	@Query("SELECT m FROM MatchRecord m WHERE m.season = ?1")
	ArrayList<MatchRecord> findBySeason(String season);

	@Query("SELECT m FROM MatchRecord m WHERE m.mode_of_data != ?1")
	ArrayList<MatchRecord> getByNotMode(String webMode);

	//@Query("UPDATE MatchRecord m SET m = ?2 WHERE m.id = ?1")
	//void update(int id, MatchRecord toUpdate);

	@Query("select m from MatchRecord m where m.season = ?1  and m.mode_of_data = ?3 and m.queue = ?2")
	List<MatchRecord> findQueueBySeason(String season, int queue , String webMode);

	@Query("SELECT m.queue FROM MatchRecord m WHERE m.date_of_match > ?1 and m.mode_of_data = ?2 order by m.date_of_match LIMIT 1")
	int findQueueByDate(Date day, String webMode);

	@Procedure(procedureName = "ADMIN.UPDATE_LAST_QUEUE")
	@Transactional
	void updateLastQueue(@Param("QUEUE_DATA") Integer QUEUE_DATA,@Param("SEASON_DATA") String SEASON_DATA);
	
	@Procedure(procedureName = "ADMIN.CHECK_PREDICTIONS")
	@Transactional
	void checkPredictionQueue(@Param("SEASON_DATA") String SEASON_DATA,@Param("QUEUE_DATA") Integer QUEUE_DATA);

}
