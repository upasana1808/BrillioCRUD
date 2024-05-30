package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Team;
@Repository
public interface TeamRepository extends JpaRepository<Team,Integer>{
	//@Query(value="select * from team t where t.name=?1",nativeQuery=true)
	@Query(value="select t from Team t where t.name=?1")
	public List<Team> findByName(String Name);
	
	/*
	@Query(value="select * from team t where t.name=?1 and t.coach=?2",nativeQuery=true)
	public List<Team> findByName(String Name,String coach);*/
	 

}
