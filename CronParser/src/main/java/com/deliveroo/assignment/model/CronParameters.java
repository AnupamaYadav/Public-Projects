package com.deliveroo.assignment.model;

import com.deliveroo.assignment.constant.Constants;
import com.deliveroo.assignment.exception.ApplicationException;
import java.util.*;

public class CronParameters {

    private final List<CronField> userInputCronFields = new ArrayList<>();
    private final String inputCommand;

    public CronParameters(String input, String version) {

        String[] values = input.split("\\s");
        CronField.Type[] cronPattern = Constants.getCronPattern(version);

        if(Objects.isNull(cronPattern) || values.length < (cronPattern.length)) {
            throw new ApplicationException("Invalid Cron Expression for given version", "Please choose valid version");
        }

        if(values.length == cronPattern.length) {
            throw new ApplicationException("Invalid Cron Expression for given version", "Missing Command");
        }

        this.inputCommand = values[values.length -1];

        for(int idx=0; idx < cronPattern.length; idx++) {
            CronField field = CronField.parseCronField(cronPattern[idx], values[idx]);
            userInputCronFields.add(field);
        }
    }

    public String getInputCommand() {
        return inputCommand;
    }

    public List<CronField> getUserInputCronFields() {
        return userInputCronFields;
    }
}
