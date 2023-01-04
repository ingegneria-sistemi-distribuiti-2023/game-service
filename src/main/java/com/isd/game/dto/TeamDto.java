package com.isd.game.dto;

public class TeamDto {
    // this DTO contains the id and the name of the team
    private int id;
    private String name;

    public TeamDto() {
    }

    public TeamDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TeamDto [id=" + id + ", name=" + name + "]";
    }
}
