package cz.karelstefan.tasklist.domain.dto;

import cz.karelstefan.tasklist.domain.TaskPriority;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class TaskDto {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    private String text;

    @NotBlank
    private String taskListToken;

    private Boolean done;

    private TaskPriority priority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTaskListToken() {
        return taskListToken;
    }

    public void setTaskListToken(String taskListToken) {
        this.taskListToken = taskListToken;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
}
