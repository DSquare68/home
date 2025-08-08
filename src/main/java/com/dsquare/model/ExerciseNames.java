package com.dsquare.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Component;

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

@AllArgsConstructor
@NoArgsConstructor
public class ExerciseNames implements Serializable {

	private int _ID;
    private String name;
    private int language;

	public ExerciseNames(String name, int language) {
		this.name = name;
		this.language = language;
	}
    public static ExerciseNames[] init(String[] data, int language) {
        ArrayList<ExerciseNames> exercises = new ArrayList<>();
        int i = 0;
        Arrays.stream(data).forEach(d -> exercises.add(new ExerciseNames(exercises.size()+1,d,language)));
        return exercises.toArray(new ExerciseNames[exercises.size()]);
    }
}
