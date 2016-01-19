package cz.karelstefan.tasklist.controller;

import cz.karelstefan.tasklist.domain.Message;
import cz.karelstefan.tasklist.domain.dto.TaskListDto;
import cz.karelstefan.tasklist.domain.entity.TaskList;
import cz.karelstefan.tasklist.service.TaskListService;
import cz.karelstefan.tasklist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/task-list")
public class TaskListController {

    @Autowired
    private TaskListService taskListService;

    @Autowired
    private TaskService taskService;

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
        model.addAttribute("tasks", taskService.findTasks(taskList));
        return "views/task-list/detail";
    }

    @RequestMapping(path = "/delete/{token}", method = RequestMethod.POST)
    public String delete(@PathVariable("token") String token, RedirectAttributes redirectAttributes) {
        TaskList taskList = requireTaskList(token);
        taskListService.deleteTaskList(taskList);
        redirectAttributes.addFlashAttribute("_message",
                new Message(Message.Type.SUCCESS, "The task list has been successfully removed"));
        return "redirect:/";
    }

    private TaskList requireTaskList(String token) {
        return taskListService.getTaskListByToken(token).orElseThrow(() -> new TaskListNotFoundException());
    }
}
