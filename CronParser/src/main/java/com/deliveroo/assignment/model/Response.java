package com.deliveroo.assignment.model;

import com.deliveroo.assignment.constant.Constants;
import com.deliveroo.assignment.constant.ErrorConstants;
import com.deliveroo.assignment.exception.ApplicationException;

import java.util.*;

public class Response {

    Map<CronField, List<String>> parsedFields;

    String command;

    public Response(Map<CronField, List<String>> parsedFields , String command) {
        this.parsedFields = parsedFields;
        this.command = command;
    }

    public Map<CronField, List<String>> getParsedFields() {
        return parsedFields;
    }

    public String getCommand() {
        return command;
    }

    public static class Builder {

        Map<CronField, List<String>> response = new HashMap<>();

        String command;

        String version;

        public Builder(String version) {
            this.version = version;
        }


        public Builder withParsedExpression(CronField field, List<String> parsedValues) {
            response.put(field, parsedValues);
            return this;
        }

        public Builder withCommand(String command) {
            this.command = command;
            return this;
        }

        public Response build() {

            CronField.Type[] requiredFields = Constants.getCronPattern(version);

            if(Objects.isNull(response) || response.size() < requiredFields.length) {
                throw new ApplicationException(ErrorConstants.ERROR_INVALID_INPUT, ErrorConstants.ERROR_INVALID_INPUT_MSG);
            }
            Map<CronField.Type, CronField> requiredEntries = new HashMap<>();
            Map<CronField, List<String>> validatedValues = new LinkedHashMap<>();
            response.entrySet().forEach(entry -> {
                if(Objects.isNull(entry.getKey()) || Objects.isNull(entry.getValue()) || entry.getValue().size() <= 0) {
                    return;
                }
                requiredEntries.put(entry.getKey().getType(), entry.getKey());

            });

            if(requiredEntries.size() < requiredFields.length) {
                throw new ApplicationException(ErrorConstants.ERROR_INVALID_INPUT, ErrorConstants.ERROR_INVALID_INPUT_MSG);
            }
            if(Objects.isNull(command) || command.trim().isEmpty()) {
                throw new ApplicationException(ErrorConstants.ERROR_MISSING_COMMAND_INPUT, ErrorConstants.ERROR_MISSING_COMMAND_INPUT_MSG);
            }

            for(CronField.Type type : requiredFields) {
                validatedValues.put(requiredEntries.get(type).copy(), Collections.unmodifiableList(response.get(requiredEntries.get(type))));
            }

            return new Response(Collections.unmodifiableMap(validatedValues), command);
        }

    }
}
