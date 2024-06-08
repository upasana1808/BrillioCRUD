package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.model.Team;
import com.example.repo.TeamRepository;
@Service
public class TeamService {
	@Autowired
	TeamRepository tr;
	public List<Team> read()
	{
		return tr.findAll(); 
	}
	public Optional<Team> readOne(int idd)
	{
		return tr.findById(idd);
	}
	public void add(Team team)
	{
		tr.save(team);
	}
	public void update( int id,Team teamNew)
	{
		Optional<Team> teamOld=tr.findById(id);
		teamOld.get().setName(teamNew.getName());
		teamOld.get().setCaptain(teamNew.getCaptain());
		teamOld.get().setExChampion(teamNew.isExChampion());
		teamOld.get().setCoach(teamNew.getCoach());	
		tr.save(teamOld.get());
	}
	public void delete(@PathVariable int id)
	{
		tr.deleteById(id);
		
	} 
	 public List<Team> findByName(String name) {
	        return tr.findByName(name); 
	    }
	
}
