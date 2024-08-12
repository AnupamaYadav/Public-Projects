package com.deliveroo.assignment.model;

import com.deliveroo.assignment.constant.Constants;
import com.deliveroo.assignment.constant.ErrorConstants;
import com.deliveroo.assignment.exception.ApplicationException;

import java.time.temporal.ChronoField;
import java.util.regex.Pattern;

public class CronField {

    public enum Type {

        MINUTE ("minute", ChronoField.MINUTE_OF_HOUR, Pattern.compile("^[0-59/*,-]+$")),
        HOUR ("hour",ChronoField.HOUR_OF_DAY, Pattern.compile("^[0-23/*,-]+$")),
        DAY_OF_MONTH("day of month",ChronoField.DAY_OF_MONTH, Pattern.compile("^[1-31/*,-LW?]+$")),
        MONTH("month",ChronoField.MONTH_OF_YEAR, Pattern.compile("^[1-12A-Za-z/*,-?]+$")),
        DAY_OF_WEEK("day of week",ChronoField.DAY_OF_WEEK, Pattern.compile("^[1-5A-Za-z/*,-L#?]+$"))

        ;
        private final String displayName;
        private final ChronoField chronoField;
        private final Pattern fieldPattern;

        Type(String name, ChronoField chronoField, Pattern fieldPattern) {
            this.chronoField = chronoField;
            this.fieldPattern = fieldPattern;
            this.displayName = name;
        }

    public String getDisplayName() {
        return displayName;
    }

    public ChronoField getChronoField() {
            return chronoField;
        }

        public Pattern getFieldPattern() {
            return fieldPattern;
        }
    }

    private final Type type;

    private final String input;

    private CronField(Type type, String inputValue) {
        this.type = type;
        this.input = inputValue;
    }

    public static CronField parseCronField(Type type, String input) {
        if (!type.fieldPattern.matcher(input).matches()) {
            throw new ApplicationException(ErrorConstants.ERROR_INVALID_INPUT, ErrorConstants.ERROR_INVALID_INPUT_MSG);
        }

        return new CronField(type, input);
    }

    public Type getType() {
        return type;
    }

    public String getInput() {
        return input;
    }

    public CronField copy() {
        return new CronField(this.type, this.input);
    }

}
