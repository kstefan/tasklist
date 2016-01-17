package cz.karelstefan.tasklist.domain.entity;

import cz.karelstefan.tasklist.domain.TaskPriority;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 255)
    private String text;

    private Boolean done;

    private TaskPriority priority;

    @ManyToOne
    private TaskList taskList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
