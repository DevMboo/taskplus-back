package com.taskplus_back.controller;

import com.taskplus_back.dto.TaskDTO;
import com.taskplus_back.entity.Task;
import com.taskplus_back.enums.StatusTask;
import com.taskplus_back.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestBody TaskDTO taskDTO,
            @CookieValue("token") String token) {
        Task novaTask = taskService.createTask(taskDTO, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasksFromUserTeam(
            @CookieValue("token") String token) {
        List<Task> tasks = taskService.findAllTasksFromUserTeam(token);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> findById(
            @PathVariable Long taskId,
            @CookieValue("token") String token) {
        Task task = taskService.findTaskById(taskId, token);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Task>> getTasksByFilter(
            @RequestParam(required = false) StatusTask status,
            @RequestParam(required = false) Long responsibleId,
            @CookieValue("token") String token) {

        if (status == null && responsibleId == null) {
            List<Task> tasks = taskService.findAllTasksFromUserTeam(token);
            return ResponseEntity.ok(tasks);
        }

        List<Task> tasks = taskService.findTasksByFilters(token, status, responsibleId);
        return ResponseEntity.ok(tasks);
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(
            @PathVariable Long taskId,
            @RequestParam StatusTask novoStatus,
            @CookieValue("token") String token) {
        Task task = taskService.changeTaskStatus(taskId, novoStatus, token);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long taskId,
            @RequestBody TaskDTO taskDTO,
            @CookieValue("token") String token) {
        Task task = taskService.updateTask(taskId, taskDTO, token);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long taskId,
            @CookieValue("token") String token) {
        taskService.deleteTask(taskId, token);
        return ResponseEntity.noContent().build();
    }
}