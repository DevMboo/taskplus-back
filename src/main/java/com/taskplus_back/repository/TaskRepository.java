package com.taskplus_back.repository;

import com.taskplus_back.entity.Task;
import com.taskplus_back.enums.StatusTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTeamId(Long teamId);
    List<Task> findByTeamIdAndStatus(Long teamId, StatusTask status);
    Optional<Task> findByIdAndTeamId(Long id, Long teamId);

    @Query("SELECT t FROM Task t WHERE " +
            "(:status IS NULL OR t.status = :status) " +
            "AND (:responsibleId IS NULL OR t.responsibleId.id = :responsibleId)")
    List<Task> findByUserIdAndFilters(
            @Param("status") StatusTask status,
            @Param("responsibleId") Long responsibleId);
}
