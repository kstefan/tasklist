package cz.karelstefan.tasklist.repository;

import cz.karelstefan.tasklist.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
