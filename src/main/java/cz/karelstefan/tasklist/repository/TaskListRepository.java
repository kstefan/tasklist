package cz.karelstefan.tasklist.repository;

import cz.karelstefan.tasklist.domain.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {

    TaskList findByToken(String token);

}
