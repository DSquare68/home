package com.dsquare.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Data
@Table(name = "EXERCISE")
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Exercise implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	private int _ID;
	@NotNull
	@JsonProperty("name")
    private String name;
	@JsonProperty("language")
    private int language;

    public static Exercise[] init(String[] data, int language) {
        ArrayList<Exercise> exercises = new ArrayList<>();
        int i = 0;
        Arrays.stream(data).forEach(d -> exercises.add(new Exercise(exercises.size()+1,d,language)));
        return exercises.toArray(new Exercise[exercises.size()]);
    }
}
