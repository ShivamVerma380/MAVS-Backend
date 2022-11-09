package com.mavs.backend.entities.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "navbar")
@Component
public class Home {

    @Id
    private String name;

    private String mainlink;

    private String submenu;

    private List<SubLink> sublinks;

    public Home() {
    }

    public Home(String name, String mainlink, String submenu, ArrayList<SubLink> sublinks) {
        this.name = name;
        this.mainlink = mainlink;
        this.submenu = submenu;
        this.sublinks = sublinks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainlink() {
        return mainlink;
    }

    public void setMainlink(String mainlink) {
        this.mainlink = mainlink;
    }

    public String getSubmenu() {
        return submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }

    public List<SubLink> getSublinks() {
        return sublinks;
    }

    public void setSublinks(List<SubLink> sublinks) {
        this.sublinks = sublinks;
    }

    @Override
    public String toString() {
        return "Home [name=" + name + ", mainlink=" + mainlink + ", submenu=" + submenu + ", sublinks=" + sublinks
                + "]";
    }

    

}
