package com.mavs.backend.entities.solution;

import org.springframework.stereotype.Component;

@Component
public class SolutionFeatures {
    
    private String name;

    private String description;

    private String icon;

    public SolutionFeatures() {
    }

    public SolutionFeatures(String name, String description, String icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "SolutionFeatures [description=" + description + ", icon=" + icon + ", name=" + name + "]";
    }

}
