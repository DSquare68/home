package com.dsquare.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsquare.db.ExerciseNames;
import com.dsquare.repository.ExerciseNamesRepository;

@Service
public class ExerciseNamesServiceImpl{

	@Autowired
	public ExerciseNamesRepository repo;
	public void insertAll(ArrayList<ExerciseNames> names) {
		repo.saveAll(names);
		//names.forEach(e->repo.insert(e));
	}
}
