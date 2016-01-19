package cz.karelstefan.tasklist.domain.entity;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 255)
    private String text;

    private Boolean done = false;

    private Byte priority = 1;

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

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        if (priority == null || priority < 1 || priority > 3) {
            throw new IllegalArgumentException("Invalid priority");
        }
        this.priority = priority;
    }
}
