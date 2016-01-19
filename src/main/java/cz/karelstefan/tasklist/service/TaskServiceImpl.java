package cz.karelstefan.tasklist.service;


import cz.karelstefan.tasklist.domain.dto.TaskDto;
import cz.karelstefan.tasklist.domain.entity.Task;
import cz.karelstefan.tasklist.domain.entity.TaskList;
import cz.karelstefan.tasklist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Optional<Task> getTask(Long id, String taskListToken) {
        return Optional.ofNullable(taskRepository.findTask(id, taskListToken));
    }

    @Override
    public Optional<TaskDto> getTaskDto(Long id, String taskListToken) {
        Optional<Task> task = getTask(id, taskListToken);
        if (task.isPresent()) {
            return Optional.of(convertTaskToDto(task.get()));
        }
        return Optional.empty();
    }

    @Override
    public TaskDto createTask(TaskList taskList, TaskDto taskDto) {
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

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public void markDone(Task task) {
        task.setDone(true);
        taskRepository.saveAndFlush(task);
    }

    @Override
    public List<Task> findTasks(TaskList taskList) {
        return taskRepository.findTasks(taskList.getToken());
    }

    private TaskDto convertTaskToDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setPriority(task.getPriority());
        dto.setText(task.getText());
        dto.setDone(task.getDone());
        return dto;
    }

}
