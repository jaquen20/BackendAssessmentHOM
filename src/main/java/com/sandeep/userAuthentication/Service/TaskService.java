package com.sandeep.userAuthentication.Service;

import com.sandeep.userAuthentication.DTO.TaskDTO;
import com.sandeep.userAuthentication.Exception.TaskNotFoundException;
import com.sandeep.userAuthentication.Exception.UserNotFoundException;
import com.sandeep.userAuthentication.Model.UserEntity;
import com.sandeep.userAuthentication.Model.UsersTask;
import com.sandeep.userAuthentication.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskRepository taskRepository;

    public TaskDTO createNewTasks(TaskDTO taskDTO, String username) {
        Optional<UserEntity> user=userService.findOptionalUserByUsername(username);
        if (user.isPresent()) {
            UsersTask usersTask = new UsersTask();
            usersTask.setTitle(taskDTO.getTitle());
            usersTask.setDescription(taskDTO.getDescription());
            usersTask.setTimestamp(LocalDateTime.now());
            usersTask.setCompleted(false);
            usersTask.setUser(user.get());
            taskRepository.save(usersTask);
            return taskDTO;
        }else throw new UserNotFoundException("User with email " + username + " not found");
    }

    public List<TaskDTO> getAllTasks(String username) {
        Optional<UserEntity> user=userService.findOptionalUserByUsername(username);
        if (user.isPresent()){
            List<UsersTask> usersTaskList=taskRepository.findByUser(user.get());
            return convertToDTO(usersTaskList);
        } else throw new UserNotFoundException("User with email " + username + " not found");
    }


    public TaskDTO updateTask(TaskDTO tasks, Long id, String username) {

        Optional<UserEntity> user=userService.findOptionalUserByUsername(username);
        if (user.isPresent()){
            UsersTask usersTask=taskRepository.findByUserAndId(user.get(),id);
            if (usersTask!=null){
                if (!tasks.getTitle().isEmpty()) usersTask.setTitle(tasks.getTitle());
                if (!tasks.getDescription().isEmpty()) usersTask.setDescription(tasks.getDescription());
                taskRepository.save(usersTask);
                return convertToDto(usersTask);
            }else throw new TaskNotFoundException( id.toString() );
        }else throw new UserNotFoundException("User with email " + username + " not found");
    }

    public void deleteTaskById(Long id, String username) {
        Optional<UserEntity> user=userService.findOptionalUserByUsername(username);
        if (user.isPresent()){
            UsersTask usersTask=taskRepository.findByUserAndId(user.get(),id);
            if (usersTask!=null){
                taskRepository.deleteById(id);
            } else throw new TaskNotFoundException( id.toString() );
        }else throw new UserNotFoundException( username );
    }



    public List<TaskDTO> convertToDTO(List<UsersTask> usersTasks) {
        return usersTasks.stream().map(this::convertToDto).collect(Collectors.toList());

    }
    public TaskDTO convertToDto(UsersTask usersTask) {
        TaskDTO taskDTO=new TaskDTO();
        taskDTO.setId(usersTask.getId());
        taskDTO.setTitle(usersTask.getTitle());
        taskDTO.setDescription(usersTask.getDescription());
        taskDTO.setTimestamp(usersTask.getTimestamp());
        taskDTO.setCompleted(usersTask.isCompleted());
        return taskDTO;
    }



}
