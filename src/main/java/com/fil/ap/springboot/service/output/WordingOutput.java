package com.fil.ap.springboot.service.output;

import java.io.Serializable;

public class WordingOutput implements Serializable {

    private String content;

    public WordingOutput() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
