package com.dsquare.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import static java.util.stream.Collectors.*;

@Entity
@Table(schema = "ADMIN", name = "EXERCISE_NAMES")
@Data
@Component
public class ExerciseNames {
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
    //@SequenceGenerator(sequenceName = "SIMPLE", allocationSize = 1, name = "SEQUENCE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@JsonProperty("id")
	private int id;
	@NotNull
	@Column(name = "NAME")
	@JsonProperty("name")
	private String name;
	
	public ExerciseNames(int id,String name) {
		this.id = id;
		this.name = name;
	}
	public ExerciseNames(String name) {
		this.name = name;
	}
	public ExerciseNames() {
		
	}
	  public static ArrayList<ExerciseNames> init(String[] data) {
	        ArrayList<ExerciseNames> exercises = new ArrayList<>();
	        for(int i = 0; i < data.length; i++)     
	        	exercises.add(new ExerciseNames(data[i]));
	        return new ArrayList<ExerciseNames>(exercises.stream().filter(e->e!=null).toList());	   
	  }
}
