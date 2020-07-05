package com.fil.ap.restful.pojo;

public class Greeting {

    private Long id;
    private String content;

    public Greeting() {
    	
    }
    
    public Greeting(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
