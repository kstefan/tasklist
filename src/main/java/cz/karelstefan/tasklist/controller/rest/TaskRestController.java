package cz.karelstefan.tasklist.controller.rest;

import cz.karelstefan.tasklist.controller.TaskListNotFoundException;
import cz.karelstefan.tasklist.domain.dto.TaskDto;
import cz.karelstefan.tasklist.domain.entity.Task;
import cz.karelstefan.tasklist.domain.entity.TaskList;
import cz.karelstefan.tasklist.service.TaskListService;
import cz.karelstefan.tasklist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/task")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskListService taskListService;

    @RequestMapping(value = "/{taskListToken}/{id}", method = RequestMethod.GET)
    public TaskDto get(@PathVariable("taskListToken") String taskListToken, @PathVariable("id") Long id) {
        return taskService.getTaskDto(id, taskListToken).orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @RequestMapping(value = "/{taskListToken}", method = RequestMethod.POST)
    public TaskDto create(@PathVariable("taskListToken") String taskListToken, @Validated @RequestBody TaskDto taskDto) {
        TaskList taskList = taskListService.getTaskListByToken(taskListToken)
                .orElseThrow(() -> new TaskListNotFoundException("Task list not found"));
        return taskService.createTask(taskList, taskDto);
    }

    @RequestMapping(value = "/{taskListToken}/{id}", method = RequestMethod.PUT)
    public TaskDto update(@PathVariable("taskListToken") String taskListToken, @PathVariable("id") Long id, @Validated @RequestBody TaskDto taskDto) {
        return taskService.updateTask(requireTask(id, taskListToken), taskDto);
    }

    @RequestMapping(value = "/{taskListToken}/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("taskListToken") String taskListToken, @PathVariable("id") Long id) {
        taskService.deleteTask(requireTask(id, taskListToken));
    }

    @RequestMapping(value = "/mark-done/{taskListToken}/{id}", method = RequestMethod.PUT)
    public void markDone(@PathVariable("taskListToken") String taskListToken, @PathVariable("id") Long id) {
        taskService.markDone(requireTask(id, taskListToken));
    }

    private Task requireTask(Long id, String taskListToken) {
        return taskService.getTask(id, taskListToken).orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

}
