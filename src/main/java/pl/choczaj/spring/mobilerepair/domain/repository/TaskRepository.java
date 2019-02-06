package pl.choczaj.spring.mobilerepair.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.choczaj.spring.mobilerepair.domain.model.Task;
import pl.choczaj.spring.mobilerepair.domain.model.TaskStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long id);

    List<Task> findAllByStatusIn(TaskStatus... statuses);

    List<Task> findAllByStatusNotIn(TaskStatus... statuses);

    List<Task> findAllByDeviceId(Long Id);

    List<Task> findAllByEmployeeId(Long Id);

    List<Task> findAllByEmployeeEmail(String email);

    List<Task> findAllByEmployeeEmailAndStatusNotIn(String email, TaskStatus... statuses);


}
