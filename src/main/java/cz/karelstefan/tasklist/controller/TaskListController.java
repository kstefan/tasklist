package cz.karelstefan.tasklist.controller;

import cz.karelstefan.tasklist.dto.TaskListDto;
import cz.karelstefan.tasklist.entity.TaskList;
import cz.karelstefan.tasklist.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/task-list")
public class TaskListController {

    @Autowired
    private TaskListService taskListService;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        TaskList taskList = new TaskList();
        model.addAttribute("taskList", taskList);
        return "views/task-list/create";
    }

    @RequestMapping(path = "/create")
    public String create(@Validated @ModelAttribute("taskList") TaskListDto taskList, BindingResult result) {
        if (result.hasErrors()) {
            return "views/task-list/create";
        }
        taskList = taskListService.createTaskList(taskList);
        return "redirect:/task-list/" + taskList.getToken();
    }

    @RequestMapping(path = "/{token}")
    public String detail(@PathVariable("token") String token, Model model) {
        TaskList taskList = requireTaskList(token);
        model.addAttribute("taskList", taskList);
        return "views/task-list/detail";
    }

    @RequestMapping(path = "/delete/{token}")
    public String delete(@PathVariable("token") String token) {
        TaskList taskList = requireTaskList(token);
        taskListService.deleteTaskList(taskList);
        // TODO set success message
        return "redirect:/";
    }

    private TaskList requireTaskList(String token) {
        return taskListService.getTaskListByToken(token).orElseThrow(() -> new TaskListNotFoundException());
    }
}
