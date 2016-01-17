package cz.karelstefan.tasklist.service;


import cz.karelstefan.tasklist.domain.dto.TaskDto;
import cz.karelstefan.tasklist.domain.entity.Task;
import cz.karelstefan.tasklist.domain.entity.TaskList;
import cz.karelstefan.tasklist.repository.TaskListRepository;
import cz.karelstefan.tasklist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Optional<Task> getTask(Long id, String taskListToken) {
        // TODO find by id and token
        return Optional.ofNullable(taskRepository.findOne(id));
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        TaskList taskList = taskListRepository.findByToken(taskDto.getTaskListToken());
        if (taskList == null) {
            throw new IllegalArgumentException("Task list not found");
        }

        Task task = new Task();
        task.setText(taskDto.getText());
        task.setPriority(taskDto.getPriority());
        task.setTaskList(taskList);
        return convertTaskToDto(taskRepository.saveAndFlush(task));
    }

    @Override
    public TaskDto updateTask(Task task, TaskDto taskDto) {
        task.setText(taskDto.getText());
        task.setPriority(taskDto.getPriority());
        task.setDone(taskDto.getDone());
        return convertTaskToDto(taskRepository.saveAndFlush(task));
    }

    private TaskDto convertTaskToDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setPriority(task.getPriority());
        dto.setText(task.getText());
        dto.setDone(task.getDone());
        dto.setTaskListToken(task.getTaskList().getToken());
        return dto;
    }

}
