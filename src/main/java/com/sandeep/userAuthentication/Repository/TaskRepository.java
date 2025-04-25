package com.sandeep.userAuthentication.Repository;

import com.sandeep.userAuthentication.Model.UserEntity;
import com.sandeep.userAuthentication.Model.UsersTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<UsersTask, Long> {
    List<UsersTask> findByUser(UserEntity userEntity);

    UsersTask findByUserAndId(UserEntity userEntity, Long id);
}
