package com.latam.unamano.controller.task;

import com.latam.unamano.dto.task.TaskDTO;
import com.latam.unamano.dto.task.TaskMapper;
import com.latam.unamano.service.task.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("api/task")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Page<TaskDTO>> getTasks(@PageableDefault(size = 9) Pageable pageable){
        return ResponseEntity.ok(taskService.findTasks(pageable).map(TaskMapper::taskToTaskDTO));
    }
/*
    @GetMapping("/on/{occupation_name}")
    public ResponseEntity<Page<TaskDTO>> getTasksByOccupation(@PageableDefault(size = 9) Pageable pageable, @PathVariable String occupation_name){
        return ResponseEntity.ok(taskService.findTasksByOccupation(pageable, occupation_name).map(TaskMapper::taskToTaskDTO));
    }
*/
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> findTaskById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(taskService.findTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO,
                                              UriComponentsBuilder uriComponentsBuilder){
        TaskDTO task = taskService.createTask(taskDTO);
        URI url = uriComponentsBuilder.path("/api/task/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(url).body(task);
    }

    @PutMapping
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO){
        return ResponseEntity.ok(taskService.updateTask(taskDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(taskService.deleteTaskById(id));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> hideTaskById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(taskService.hideTaskById(id));
    }

    @GetMapping("/published")
    public ResponseEntity<Page<TaskDTO>> findByPublished(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.findTaskByStatusPUBLISHED(pageable)
                        .map(TaskMapper::taskToTaskDTO));
    }






}
