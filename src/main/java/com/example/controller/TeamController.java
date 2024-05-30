package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Team;
import com.example.repo.TeamRepository;
import com.example.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {
	@Autowired 
	TeamService ts;
	
	@Autowired
	TeamRepository tr;
	
	@GetMapping("/read")
	public List<Team> read()
	{
		return ts.read();
	}
	
	@GetMapping("/readname/{name}")
	public List<Team> readByName(@PathVariable String name)
	{
		return tr.findByName(name); 
	}
	@GetMapping("/readone/{id}")
	public Optional<Team> readOne(@PathVariable int id)
	{
		return ts.readOne(id);
	}
	//add
	@PostMapping("/add")
	public void add(@RequestBody Team team)
	{
		tr.save(team);
	}	
	//update
	@PutMapping("/update/{id}")
	public void update(@RequestBody Team teamNew,@PathVariable int id)
	{
		ts.update(id, teamNew);
	}	
	//delete
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id)
	{
		ts.delete(id); 
	}

}
