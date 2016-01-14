package cz.karelstefan.tasklist.service;

import cz.karelstefan.tasklist.dto.TaskListDto;
import cz.karelstefan.tasklist.entity.TaskList;

import java.util.Optional;

public interface TaskListService {


    Optional<TaskList> getTaskListByToken(String token);

    Optional<TaskListDto> getTaskListDtoByToken(String token);

    TaskListDto createTaskList(TaskListDto dto);

    TaskListDto updateTaskList(TaskList taskList, TaskListDto input);

    void deleteTaskList(TaskList taskList);
}
