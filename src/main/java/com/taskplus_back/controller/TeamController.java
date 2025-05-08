package com.taskplus_back.controller;

import com.taskplus_back.dto.TeamDTO;
import com.taskplus_back.entity.Team;
import com.taskplus_back.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<Team>> findAll() {
        List<Team> teams = teamService.findAll();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> findById(@PathVariable Long id) {
        Team team = teamService.findById(id); // Exceção será lançada pelo service se não encontrar
        return ResponseEntity.ok(team);
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody TeamDTO teamDTO) {
        Team savedTeam = teamService.save(teamDTO);
        return ResponseEntity.status(201).body(savedTeam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(
            @PathVariable Long id,
            @RequestBody TeamDTO teamDTO) {
        Team updatedTeam = teamService.update(id, teamDTO);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}