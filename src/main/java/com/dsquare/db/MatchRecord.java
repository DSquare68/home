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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "ADMIN", name = "MATCHES")
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class MatchRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
    @SequenceGenerator(sequenceName = "SIMPLE", allocationSize = 1, name = "SEQUENCE")
	@JsonProperty("ID")
	private int ID;
	private String home;
	private String guest;
	private int homeResult;
	private int guestResult;
	private Date date_of_match;
	private String cup;
	private String season;
}
