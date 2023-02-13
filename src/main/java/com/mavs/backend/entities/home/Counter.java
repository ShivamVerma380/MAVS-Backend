package com.mavs.backend.entities.home;

import org.springframework.data.annotation.Id;

public class Counter {
    
    @Id
    private String parameter;

    private String count;

    

    public Counter() {
    }

    

    public Counter(String parameter, String count) {
        this.parameter = parameter;
        this.count = count;
    }



    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }



    @Override
    public String toString() {
        return "Counter [parameter=" + parameter + ", count=" + count + "]";
    }

    

    
}
