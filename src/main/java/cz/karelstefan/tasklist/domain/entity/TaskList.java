package cz.karelstefan.tasklist.domain.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class TaskList {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(unique = true)
    private String token;

    @OneToMany(mappedBy = "taskList", orphanRemoval=true)
    @OrderBy("priority DESC, id DESC")
    private List<Task> tasks;

    public TaskList() {
        token = UUID.randomUUID().toString();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
