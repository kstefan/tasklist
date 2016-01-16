package cz.karelstefan.tasklist.controller;

import cz.karelstefan.tasklist.domain.dto.TaskListDto;
import cz.karelstefan.tasklist.domain.entity.TaskList;
import cz.karelstefan.tasklist.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/task-list")
public class TaskListRestController {

    @Autowired
    private TaskListService taskListService;

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public TaskListDto get(@PathVariable(value = "token") String token) {
        return taskListService.getTaskListDtoByToken(token).orElseThrow(() -> new TaskListNotFoundException());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public TaskListDto create(@Validated @RequestBody TaskListDto taskList) {
        return taskListService.createTaskList(taskList);
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.PUT)
    public TaskListDto update(@PathVariable(value = "token") String token, @Validated @RequestBody TaskListDto taskListInput) {
        TaskList taskList = requireTaskList(token);

        return taskListService.updateTaskList(taskList, taskListInput);
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "token") String token) {
        taskListService.deleteTaskList(requireTaskList(token));
    }

    private TaskList requireTaskList(String token) {
        return taskListService.getTaskListByToken(token).orElseThrow(() -> new TaskListNotFoundException());
    }

}
