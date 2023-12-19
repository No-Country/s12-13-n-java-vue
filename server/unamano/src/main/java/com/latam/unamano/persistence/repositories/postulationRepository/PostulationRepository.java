package com.latam.unamano.persistence.repositories.postulationRepository;

import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import com.latam.unamano.persistence.entities.task.Task;
import com.latam.unamano.utils.PostulationStatus;
import com.latam.unamano.utils.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulationRepository extends JpaRepository<Postulation, Long> {


    Page<Postulation> getAllByWorkerId(Pageable pageable, @Param("id") Long id);

    Page<Postulation>  getAllByTaskId(Pageable pageable, @Param("id")Long idTask);

    List<Postulation> getAllByTaskId(@Param("id") Long id);

    List<Postulation> findByWorkerIdAndTaskId(@Param("worker_id")Long worker_id, @Param("task_id")Long task_id);

    Page<Postulation> findByWorkerIdAndTaskStatusAndStatus(Pageable pageable, @Param("worker_id")Long worker_id, @Param("task_status")TaskStatus task_status
    , @Param("status") PostulationStatus status);
}
