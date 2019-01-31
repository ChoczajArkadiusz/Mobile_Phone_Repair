package pl.choczaj.spring.mobilerepair.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.choczaj.spring.mobilerepair.domain.model.Task;
import pl.choczaj.spring.mobilerepair.domain.model.TaskStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long id);

//    @Query("SELECT t FROM tasks t WHERE t.status <> 'REPAIRED' AND t.status <> 'DELIVERED' AND t.status <> 'CANCELED'")
//    List<Task> findAllInProgres();

    List<Task> findAllByStatusIn(TaskStatus... statuses);

    List<Task> findAllByStatusNotIn(TaskStatus... statuses);

    List<Task> findAllByDeviceId(Long Id);

//    @Query("SELECT t FROM tasks t WHERE t.employee_id = ?1")
    List<Task> findAllTasksByEmployeeId(Long Id);

}
