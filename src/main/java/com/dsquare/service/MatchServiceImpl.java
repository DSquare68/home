package com.dsquare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsquare.db.MatchRecord;
import com.dsquare.repository.MatchRespository;

@Service
public class MatchServiceImpl {
	@Autowired
	private MatchRespository matchRespository;
	
	public void addMatchRecord(MatchRecord record) {
		matchRespository.save(record);
	}
	
	public void addMatchesRecord(Iterable<MatchRecord> record) {
		matchRespository.saveAll(record);
	}
}
