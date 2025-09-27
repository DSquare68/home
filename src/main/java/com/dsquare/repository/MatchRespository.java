package com.dsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsquare.db.MatchRecord;

@Repository
public interface MatchRespository extends JpaRepository<MatchRecord, Integer> {

}
