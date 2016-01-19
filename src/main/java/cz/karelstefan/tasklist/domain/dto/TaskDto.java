package cz.karelstefan.tasklist.domain.dto;

import cz.karelstefan.tasklist.domain.TaskPriority;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskDto {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    private String text;

    private Boolean done;

    @NotNull
    @Min(1)
    @Max(3)
    private Byte priority;

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
        this.priority = priority;
    }
}
