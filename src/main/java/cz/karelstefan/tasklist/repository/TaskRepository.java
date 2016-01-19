package cz.karelstefan.tasklist.repository;

import cz.karelstefan.tasklist.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t join t.taskList tl where t.id = ?1 and tl.token = ?2")
    Task findTask(Long id, String taskListToken);

    @Query("select t from Task t join t.taskList tl where tl.token = ?1 and (t.done is null or t.done = false) order by t.priority desc, t.id desc")
    List<Task> findTasks(String taskListToken);
}
