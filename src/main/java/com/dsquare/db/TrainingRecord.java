package com.dsquare.db;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "ADMIN", name = "TRAININGS")
@Data
@Component
@AllArgsConstructor
public class TrainingRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
    @SequenceGenerator(sequenceName = "SIMPLE", allocationSize = 1, name = "SEQUENCE")
	private int ID;
	@JsonProperty("ID_TRAINING")
	private int ID_TRAINING;
	@JsonProperty("ID_EXERCISE_NAME")
	private int ID_EXERCISE_NAME;
	@JsonProperty("SERIE")
	private int SERIE;
	@JsonProperty("REPEAT")
	private int REPEAT;
	@JsonProperty("WEIGHT")
	private double WEIGHT;
	@JsonProperty("SCHEMA")
	private int SCHEMA;
	@JsonProperty("IS_SCHEMA")
	private int IS_SCHEMA;
	@JsonProperty("DATE_TRAINING")
	private Date DATE_TRAINING;
	@JsonProperty("NAME_SCHEMA")
	private String NAME_SCHEMA;
	
	public TrainingRecord(int id_training, int id_exercise_name, int serie, int repeat, double weight,
			int schema, int is_schema, Date date_training, String name_schema) {
		this.ID_TRAINING = id_training;
		this.ID_EXERCISE_NAME = id_exercise_name;
		this.SERIE = serie;
		this.REPEAT = repeat;
		this.WEIGHT = weight;
		this.SCHEMA = schema;
		this.IS_SCHEMA = is_schema;
		this.DATE_TRAINING = date_training;
		this.NAME_SCHEMA = name_schema;
	}

	public TrainingRecord() {
		// Default constructor
	}
}
