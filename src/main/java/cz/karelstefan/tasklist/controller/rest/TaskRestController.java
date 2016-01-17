package cz.karelstefan.tasklist.controller.rest;

import cz.karelstefan.tasklist.domain.dto.TaskDto;
import cz.karelstefan.tasklist.domain.entity.Task;
import cz.karelstefan.tasklist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/task")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.POST)
    public TaskDto create(@Validated @RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TaskDto update(@PathVariable("id") Long id, @Validated @RequestBody TaskDto taskDto) {
        return taskService.updateTask(requireTask(id, taskDto.getTaskListToken()), taskDto);
    }

    private Task requireTask(Long id, String taskListToken) {
        return taskService.getTask(id, taskListToken).orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

}
