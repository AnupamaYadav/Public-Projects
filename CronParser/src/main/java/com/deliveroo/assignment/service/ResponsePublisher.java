package com.deliveroo.assignment.service;

import com.deliveroo.assignment.constant.Constants;
import com.deliveroo.assignment.model.Response;

public class ResponsePublisher {

    public void publish(Response responseData) {

        responseData.getParsedFields().forEach((key, value) ->
                System.out.println(String.format("%-20s", key.getType().getDisplayName()) + String.join(" ", value)));
        System.out.println(String.format("%-20s", Constants.COMMAND) + responseData.getCommand());
    }
}
