package com.latam.unamano.persistence.repositories.task;


import com.latam.unamano.persistence.entities.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.status=PUBLISHED")
    Page<Task> findByStatusPUBLISHED(Pageable pageable);
<<<<<<< HEAD

    @Query("SELECT t FROM Task t INNER JOIN t.occupations o WHERE o.occupationName=:occupationName AND t.status=PUBLISHED")
    Page<Task> findByOccupationOccupationName(Pageable pageable, @Param("occupationName") String occupationName);




=======
    @Query("SELECT t FROM Task t INNER JOIN t.occupations o WHERE o.occupationName=:occupationName AND t.status=PUBLISHED")
    Page<Task> findByOccupationOccupationName(Pageable pageable, @Param("occupationName") String occupationName);

>>>>>>> dev-backend
}
