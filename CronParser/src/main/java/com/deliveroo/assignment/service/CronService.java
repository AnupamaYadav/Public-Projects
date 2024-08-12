package com.deliveroo.assignment.service;

import com.deliveroo.assignment.model.CronField;
import com.deliveroo.assignment.model.CronParameters;
import com.deliveroo.assignment.model.Response;
import java.util.List;

public class CronService {

    ResponsePublisher responseBuilder;
    BaseCronParser baseCronParser;

    public CronService() {
        this.responseBuilder = new ResponsePublisher();
        this.baseCronParser = new BaseCronParser();
    }

    public void handle(String input, String version) {

        CronParameters cronParams = new CronParameters(input, version);
        Response.Builder builder = new Response.Builder(version);

        for(CronField cronField : cronParams.getUserInputCronFields()) {
            int minimumValue = (int) cronField.getType().getChronoField().range().getMinimum();
            int maximumValue = (int) cronField.getType().getChronoField().range().getMaximum();
            List<String> parsedValues = baseCronParser.parseExpression(cronField.getInput(), minimumValue, maximumValue);
            builder.withParsedExpression(cronField, parsedValues);
        }
        builder.withCommand(cronParams.getInputCommand());
        Response response = builder.build();
        responseBuilder.publish(response);
    }

}
