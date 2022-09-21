package com.mavs.backend.entities.solution;

import org.springframework.stereotype.Component;

@Component
public class SolutionBenefits {
    
    private String name;

    private String icon;

    private String description;

    public SolutionBenefits() {
    }

    public SolutionBenefits(String name, String icon, String description) {
        this.name = name;
        this.icon = icon;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SolutionBenefits [description=" + description + ", icon=" + icon + ", name=" + name + "]";
    }
}
