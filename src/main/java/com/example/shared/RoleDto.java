package com.example.shared;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


public class RoleDto {

    @NotNull(groups = {ValidateGroups.Update.class})
    private Long id;

    @NotEmpty(groups = {ValidateGroups.Create.class})
    private String name;

    public RoleDto(){

    }

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
}
