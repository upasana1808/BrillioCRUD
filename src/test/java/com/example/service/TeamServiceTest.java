package com.example.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.model.Team;
import com.example.repo.TeamRepository;
import com.example.service.TeamService;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    private Team team1;
    private Team team2;

    @BeforeEach
    void setUp() {
        team1 = new Team();
        team1.setId(1);
        team1.setName("Team A");
        team1.setCaptain("Captain A");
        team1.setExChampion(true);
        team1.setCoach("Coach A");

        team2 = new Team();
        team2.setId(2);
        team2.setName("Team B");
        team2.setCaptain("Captain B");
        team2.setExChampion(false);
        team2.setCoach("Coach B");
    }
    @Test
    void testRead() {
        List<Team> teams = Arrays.asList(team1, team2);
        when(teamRepository.findAll()).thenReturn(teams);

        List<Team> result = teamService.read();
        assertEquals(2, result.size());
        assertEquals("Team A", result.get(0).getName());
        assertEquals("Team B", result.get(1).getName());
    }
    @Test
    void testReadOne() {
        when(teamRepository.findById(1)).thenReturn(Optional.of(team1));

        Optional<Team> result = teamService.readOne(1);
        assertTrue(result.isPresent());
        assertEquals("Team A", result.get().getName());
    }
    @Test
    void testAdd() {
        teamService.add(team1);
        verify(teamRepository).save(team1);
    }
    @Test
    void testUpdate() {
        Team updatedTeam = new Team();
        updatedTeam.setName("Updated Team A");
        updatedTeam.setCaptain("Updated Captain A");
        updatedTeam.setExChampion(false);
        updatedTeam.setCoach("Updated Coach A");
        when(teamRepository.findById(1)).thenReturn(Optional.of(team1));
        teamService.update(1, updatedTeam);
        assertEquals("Updated Team A", team1.getName());
        assertEquals("Updated Captain A", team1.getCaptain());
        assertEquals(false, team1.isExChampion());
        assertEquals("Updated Coach A", team1.getCoach());
        verify(teamRepository).save(team1);
    }
    @Test
    void testDelete() {
        teamService.delete(1);
        verify(teamRepository).deleteById(1);
    }
    
    
}


//package com.example.repo;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import static org.mockito.BDDMockito.given;
//import static org.assertj.core.api.Assertions.assertThat;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import com.example.model.Team;
//import com.example.service.TeamService;
//@ExtendWith(MockitoExtension.class)
//public class TeamServiceTest {
//	@Mock
//	private TeamRepository teamRepo;
//	@InjectMocks
//	private TeamService teamService;
//	@Test
//	  void getAllTeam()
//	    {
//	      //given
//	       Team team1= new Team(1,"KKR","Sheyash",false,"Grand Flower" );
//	       Team team2= new Team(2,"RCB","Virat kohli",false,"Rahul Dravid");
//	      //When
//	       given(teamRepo.findAll())
//	      .willReturn(List.of(team1,team2));
//	       List<Team> teamList = teamService.read();
//	       System.out.println(teamList);
//	       //Then
//	    //Make sure to import ass ertThat From org.assertj.core.api package
//	       assertThat(teamList).isNotNull();
//	       assertThat(teamList.size()).isEqualTo(2);
//	  }
//
//}
