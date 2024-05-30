package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Team {
	@Id  //primary key
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;	
	
	private String name;
	private String captain;
	private boolean exChampion;
	private String coach;

}
