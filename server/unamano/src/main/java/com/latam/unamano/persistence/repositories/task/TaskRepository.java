package com.latam.unamano.persistence.repositories.task;


import com.latam.unamano.persistence.entities.task.Task;
import com.latam.unamano.utils.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.status=PUBLISHED")
    Page<Task> findByStatusPUBLISHED(Pageable pageable);
    @Query("SELECT t FROM Task t INNER JOIN t.occupations o WHERE o.occupationName=:occupationName AND t.status=PUBLISHED")
    Page<Task> findByOccupationOccupationName(Pageable pageable, @Param("occupationName") String occupationName);

    Page<Task> findByClientId(Pageable pageable, @Param("id") Long id);
    @Query("SELECT t FROM Task t INNER JOIN t.client c WHERE c.id=:id AND t.status=:status")
    Page<Task> findByClientIdAndStatus(Pageable pageable,@Param("id") Long id, @Param("status") TaskStatus status);



}
