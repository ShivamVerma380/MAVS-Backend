package com.mavs.backend.entities.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
public class SubLink {
    
    private String Head;

    private List<String> sublink;

    public SubLink() {
    }

    public SubLink(String head, ArrayList<String> sublink) {
        Head = head;
        this.sublink = sublink;
    }

    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        Head = head;
    }

    public List<String> getSublink() {
        return sublink;
    }

    public void setSublink(List<String> sublink) {
        this.sublink = sublink;
    }

    @Override
    public String toString() {
        return "SubLinks [Head=" + Head + ", sublink=" + sublink + "]";
    }

    
}
