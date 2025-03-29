package com.dsquare.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Component;

import com.dsquare.model.Round;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Data
@Table(name = "EXERCISE")
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseTable implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")
	@SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1)
	@JsonProperty("id")
	private long _ID;
	@NotNull
	@JsonProperty("name_id")
    private long name;
	@OneToMany
	@ElementCollection
	@CollectionTable(name = "rounds", joinColumns = @jakarta.persistence.JoinColumn(name = "exercise_id"))
	private ArrayList<Round> rounds = new ArrayList<Round>();
	
	@JsonProperty("language")
    private int language;

  
}
