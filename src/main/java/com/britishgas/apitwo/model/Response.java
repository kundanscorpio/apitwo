package com.britishgas.apitwo.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kundan Sharma
 */
@Data
public class Response {

    List<String> messages = new ArrayList<String>();

    public void addResponse(String newMessage) {
        messages.add(newMessage);
    }
}