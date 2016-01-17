package cz.karelstefan.tasklist.service;

import cz.karelstefan.tasklist.domain.dto.TaskDto;
import cz.karelstefan.tasklist.domain.entity.Task;
import cz.karelstefan.tasklist.domain.entity.TaskList;

import java.util.Optional;

public interface TaskService {

    Optional<Task> getTask(Long id, String taskListToken);

    Optional<TaskDto> getTaskDto(Long id, String taskListToken);

    TaskDto createTask(TaskList taskList, TaskDto taskDto);

    TaskDto updateTask(Task task, TaskDto taskDto);

    void deleteTask(Task task);

    void markDone(Task task);
}
