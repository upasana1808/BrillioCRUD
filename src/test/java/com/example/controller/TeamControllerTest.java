package com.example.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.model.Team;
import com.example.repo.TeamRepository;
import com.example.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TeamController.class)
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TeamService teamService;
    @MockBean
    private TeamRepository teamRepository;
    @InjectMocks
    private TeamController teamController;
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

        mockMvc = MockMvcBuilders.standaloneSetup(teamController).build();
    }

    @Test
    void testRead() throws Exception {
        List<Team> teams = Arrays.asList(team1, team2);
        when(teamService.read()).thenReturn(teams);

        mockMvc.perform(get("/team/read"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Team A"))
                .andExpect(jsonPath("$[1].name").value("Team B"));
    }
    @Test
    void testReadByName() throws Exception {
        List<Team> teams = Arrays.asList(team1);
        when(teamRepository.findByName("Team A")).thenReturn(teams);

        mockMvc.perform(get("/team/readname/Team A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Team A"));
    }    
    @Test
    void testReadOne() throws Exception {
        when(teamService.readOne(1)).thenReturn(Optional.of(team1));

        mockMvc.perform(get("/team/readone/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Team A"));
    }
    @Test
    void testAdd() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String teamJson = objectMapper.writeValueAsString(team1);

        mockMvc.perform(post("/team/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(teamJson))
                .andExpect(status().isOk());

        verify(teamRepository).save(team1);
    }
    @Test
    void testUpdate() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(); 
        String teamJson = objectMapper.writeValueAsString(team1);

        mockMvc.perform(put("/team/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(teamJson))
                .andExpect(status().isOk());

        verify(teamService).update(1, team1);
    }

    @Test 
    void testDelete() throws Exception {
        mockMvc.perform(delete("/team/delete/1"))
                .andExpect(status().isOk());

        verify(teamService).delete(1);
    }
}
