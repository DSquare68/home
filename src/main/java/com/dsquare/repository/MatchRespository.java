package com.dsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dsquare.db.MatchRecord;

public interface MatchRespository extends JpaRepository<MatchRecord, Integer> {

}
