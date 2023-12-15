package com.latam.unamano.controller.task;

import com.latam.unamano.dto.task.TaskDTO;
import com.latam.unamano.dto.task.TaskMapper;
import com.latam.unamano.service.task.TaskService;
import com.latam.unamano.utils.TaskStatus;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("api/task")
@SecurityRequirement(name = "bearer-key")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Page<TaskDTO>> getTasks(@PageableDefault(size = 9) Pageable pageable){
        return ResponseEntity.ok(taskService.findTasks(pageable).map(TaskMapper::taskToTaskDTO));
    }

    @GetMapping("/on/{occupation_name}")
    public ResponseEntity<Page<TaskDTO>> getTasksByOccupation(@PageableDefault(size = 9) Pageable pageable, @PathVariable String occupation_name){
        return ResponseEntity.ok(taskService.findTasksByOccupation(pageable, occupation_name).map(TaskMapper::taskToTaskDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> findTaskById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(taskService.findTaskById(id));
    }

    @PostMapping
    @Secured("ROLE_CLIENT")
    public ResponseEntity<TaskDTO> createTask( @RequestBody TaskDTO taskDTO,
                                              UriComponentsBuilder uriComponentsBuilder){
        TaskDTO task = taskService.createTask(taskDTO);
        URI url = uriComponentsBuilder.path("/api/task/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(url).body(task);
    }

    @PutMapping
    @Secured("ROLE_CLIENT")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO){
        return ResponseEntity.ok(taskService.updateTask(taskDTO));
    }

    @DeleteMapping("{id}")
    @Secured("ROLE_CLIENT")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(taskService.deleteTaskById(id));
    }
    @DeleteMapping("delete/{id}")
    @Secured("ROLE_CLIENT")
    public ResponseEntity<String> hideTaskById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(taskService.hideTaskById(id));
    }

    @GetMapping("/published")
    public ResponseEntity<Page<TaskDTO>> findByPublished(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.findTaskByStatusPUBLISHED(pageable)
                        .map(TaskMapper::taskToTaskDTO));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Page<TaskDTO>> findByClientID(Pageable pageable, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findByClientId(pageable, id));
    }

    @GetMapping("/client/")
    public ResponseEntity<Page<TaskDTO>> findByClientIDAndStatus(Pageable pageable, @PathParam("id") Long id, @PathParam("status") TaskStatus status){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findByClientIdAndStatus(pageable, id, status));
    }






}
