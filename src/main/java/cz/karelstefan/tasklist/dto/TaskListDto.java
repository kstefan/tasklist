package cz.karelstefan.tasklist.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class TaskListDto {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
