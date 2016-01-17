package cz.karelstefan.tasklist.service;

import cz.karelstefan.tasklist.domain.dto.TaskDto;
import cz.karelstefan.tasklist.domain.entity.Task;

import java.util.Optional;

public interface TaskService {

    Optional<Task> getTask(Long id, String taskListToken);

    TaskDto createTask(TaskDto taskDto);

    TaskDto updateTask(Task task, TaskDto taskDto);
}
