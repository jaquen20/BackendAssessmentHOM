package com.sandeep.userAuthentication.Controller;

import com.sandeep.userAuthentication.DTO.TaskDTO;
import com.sandeep.userAuthentication.Model.UsersTask;
import com.sandeep.userAuthentication.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.List;

@RestController
@RequestMapping("/userTasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @PostMapping("/add")
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
        TaskDTO task = taskService.createNewTasks(taskDTO,auth.getName());
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }else return new  ResponseEntity<>("user is not authorised",HttpStatus.UNAUTHORIZED);
    }
    @GetMapping
    public ResponseEntity<?> ViewAllTask(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            List<TaskDTO> allTasks=taskService.getAllTasks(authentication.getName());
            return ResponseEntity.ok(allTasks);
        }
        else return new  ResponseEntity<>("user is not authorised",HttpStatus.UNAUTHORIZED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id,@RequestBody TaskDTO tasks){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()){
            TaskDTO taskDTO=taskService.updateTask(tasks,id,authentication.getName());
            return new ResponseEntity<>("Task updated \n"+tasks,HttpStatus.OK);
        }else return new  ResponseEntity<>("user is not authorised",HttpStatus.UNAUTHORIZED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>DeleteTask(@PathVariable Long id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            taskService.deleteTaskById(id, authentication.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new  ResponseEntity<>("user is not authorised",HttpStatus.UNAUTHORIZED);
    }
}
