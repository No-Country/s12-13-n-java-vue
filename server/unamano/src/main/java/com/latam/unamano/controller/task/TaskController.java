package com.latam.unamano.controller.task;

import com.latam.unamano.dto.task.CreateTaskDTO;
import com.latam.unamano.dto.task.TaskDTO;
import com.latam.unamano.dto.task.TaskMapper;
import com.latam.unamano.dto.task.UpdateTaskDTO;
import com.latam.unamano.service.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
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
    @Operation(summary = "Endpoint que devuelve todas las tareas de la base de datos.  " +
            "El resultado se devuelve paginado, por defecto en grupos de 20 tareas, ordenadas" +
            "por su id de forma ascendente y desde la página 0(que representa la primera página). Estos datos pueden modificarse" +
            "según las necesidades. " +
            "Para modificar los parámetros por defecto pueden adosarse a la url del endpoint estos datos, de forma" +
            "individual o usarse más de uno alternando el símbolo & entre uno y otro: " +
            "size=<n>modifica el tamaño de la página según el valor que se asigne a n. Ejemplo" +
            "/published?size=6 " +
            "En este ejemplo retorna las tareas en páginas con 6 tareas cada una" +
            "page=<n> retorna la página que figura en n " +
            "/published?page=1 " +
            "En este ejemplo retorna las tareas de la página 1 (si es que existiera, sino llega vacía) " +
            "sort=<atributo>  retorna las páginas ordenadas de acuerdo al atributo elegido. " +
            "/published?sort=taskDate,DESC" +
            "En este ejemplo retorna las tareas ordenadas por fecha de alta o fecha en que se realiza la tara, " +
            "desde las más nuevas a las más viejas en la base de datos. También pueden retornarse en orden ascendente" +
            "cambiando DESC por ASC")
    public ResponseEntity<Page<TaskDTO>> getTasks(@PageableDefault(size = 9) Pageable pageable){
        return ResponseEntity.ok(taskService.findTasks(pageable).map(TaskMapper::taskToTaskDTO));
    }

    @GetMapping("/on/{occupation_name}")
    @Operation(summary = "Endpoint que devuelve las tareas con la ocupación o categoría ingresada(occupation_name). " +
            "El resultado se devuelve paginado, por defecto en grupos de 20 tareas, ordenadas" +
            "por su id de forma ascendente y desde la página 0(que representa la primera página). Estos datos pueden modificarse" +
            "según las necesidades. " +
            "Para modificar los parámetros por defecto pueden adosarse a la url del endpoint estos datos, de forma" +
            "individual o usarse más de uno alternando el símbolo & entre uno y otro: " +
            "size=<n>modifica el tamaño de la página según el valor que se asigne a n. Ejemplo" +
            "/published?size=6 " +
            "En este ejemplo retorna las tareas en páginas con 6 tareas cada una" +
            "page=<n> retorna la página que figura en n " +
            "/published?page=1 " +
            "En este ejemplo retorna las tareas de la página 1 (si es que existiera, sino llega vacía) " +
            "sort=<atributo>  retorna las páginas ordenadas de acuerdo al atributo elegido. " +
            "/published?sort=taskDate,DESC" +
            "En este ejemplo retorna las tareas ordenadas por fecha de alta o fecha en que se realiza la tara, " +
            "desde las más nuevas a las más viejas en la base de datos. También pueden retornarse en orden ascendente" +
            "cambiando DESC por ASC")
    public ResponseEntity<Page<TaskDTO>> getTasksByOccupation(@PageableDefault(size = 20) Pageable pageable, @PathVariable String occupation_name){
        return ResponseEntity.ok(taskService.findTasksByOccupation(pageable, occupation_name).map(TaskMapper::taskToTaskDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint que retorna todos los datos de la tarea según su id")
    public ResponseEntity<TaskDTO> findTaskById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(taskService.findTaskById(id));
    }

    @PostMapping
    @Secured("ROLE_CLIENT")
    @Operation(summary = "Endpoint para que un cliente cree tareas/ofertas de trabajo. Para funcionar requiere que se envíe el JWT " +
            ", además de un título, descripción, precio, tipo de moneda, un array de categorías y la dirección. " +
            "Los demás datos se setean por defecto, incluída la id  del cliente.")
    public ResponseEntity<TaskDTO> createTask(@RequestBody CreateTaskDTO createTaskDTO,
                                              UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request){
        TaskDTO task = taskService.createTask(createTaskDTO, request);
        URI url = uriComponentsBuilder.path("/api/task/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(url).body(task);
    }

    @PutMapping("{id}")
    @Secured("ROLE_CLIENT")
    @Operation(summary = "Endpoint para actualizar los datos de una tarea. En este momento sólo se permite actualizar una tarea" +
            "que se encuentra en estado PUBLISHED, y no aquellas que están en progreso, finalizadas o canceladas. Los datos que se pueden" +
            "modificar son el título, la descripción, el precio, el tipo de moneda, y la fecha. Es requerido enviar el id de la tarea" +
            "que se desea modificar.")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody UpdateTaskDTO updateTaskDTO, @PathVariable Long id){
        return ResponseEntity.ok(taskService.updateTask(updateTaskDTO, id));
    }

    @DeleteMapping("{id}")
    @Secured("ROLE_CLIENT")
    @Operation(summary = "Endpoint que elimina definitivamente de la base de datos la tarea con el id ingresado")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(taskService.deleteTaskById(id));
    }
    @DeleteMapping("delete/{id}")
    @Secured("ROLE_CLIENT")
    @Operation(summary = "Endpoint que cambia el status de una tarea en la base de datos con el id ingresado. " +
            "Simula la eliminación de la tarea, dejándola deshabilitada para ser usada en otros endpoints")
    public ResponseEntity<String> hideTaskById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(taskService.hideTaskById(id));
    }

    @GetMapping("/published")
    @Operation(summary = "Endpoint que devuelve las tareas que se encuentran en estado publicadas para ser vistas por un " +
            "trabajador y poder postularse. El resultado se devuelve paginado, por defecto en grupos de 20 tareas, ordenadas" +
            "por su id de forma ascendente y desde la página 0(que representa la primera página). Estos datos pueden modificarse" +
            "según las necesidades. " +
            "Para modificar los parámetros por defecto pueden adosarse a la url del endpoint estos datos, de forma" +
            "individual o usarse más de uno alternando el símbolo & entre uno y otro: " +
            "size=<n>modifica el tamaño de la página según el valor que se asigne a n. Ejemplo" +
            "/published?size=6 " +
            "En este ejemplo retorna las tareas en páginas con 6 tareas cada una" +
            "page=<n> retorna la página que figura en n " +
            "/published?page=1 " +
            "En este ejemplo retorna las tareas de la página 1 (si es que existiera, sino llega vacía) " +
            "sort=<atributo>  retorna las páginas ordenadas de acuerdo al atributo elegido. " +
            "/published?sort=taskDate,DESC" +
            "En este ejemplo retorna las tareas ordenadas por fecha de alta o fecha en que se realiza la tara, " +
            "desde las más nuevas a las más viejas en la base de datos. También pueden retornarse en orden ascendente" +
            "cambiando DESC por ASC")
    public ResponseEntity<Page<TaskDTO>> findByPublished(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.findTaskByStatusPUBLISHED(pageable)
                        .map(TaskMapper::taskToTaskDTO));
    }






}
