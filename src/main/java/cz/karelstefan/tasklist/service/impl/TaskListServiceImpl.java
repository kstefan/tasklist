package cz.karelstefan.tasklist.service.impl;

import cz.karelstefan.tasklist.domain.dto.TaskListDto;
import cz.karelstefan.tasklist.domain.entity.TaskList;
import cz.karelstefan.tasklist.repository.TaskListRepository;
import cz.karelstefan.tasklist.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskListServiceImpl implements TaskListService {

    @Autowired
    private TaskListRepository taskListRepository;

    @Override
    public Optional<TaskList> getTaskListByToken(String token) {
        return Optional.ofNullable(taskListRepository.findByToken(token));
    }

    @Override
    public Optional<TaskListDto> getTaskListDtoByToken(String token) {
        Optional<TaskList> taskList = getTaskListByToken(token);
        if (taskList.isPresent()) {
            return Optional.of(convertTaskListToDto(taskList.get()));
        }
        return Optional.empty();
    }

    @Override
    public TaskListDto createTaskList(TaskListDto dto) {
        TaskList taskList = new TaskList();
        taskList.setName(dto.getName());

        return convertTaskListToDto(taskListRepository.saveAndFlush(taskList));
    }

    @Override
    public TaskListDto updateTaskList(TaskList taskList, TaskListDto input) {
        taskList.setName(input.getName());

        return convertTaskListToDto(taskListRepository.saveAndFlush(taskList));
    }

    @Override
    public void deleteTaskList(TaskList taskList) {
        taskListRepository.delete(taskList);
    }

    private TaskListDto convertTaskListToDto(TaskList entity) {
        TaskListDto dto = new TaskListDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setToken(entity.getToken());

        return dto;
    }

}
