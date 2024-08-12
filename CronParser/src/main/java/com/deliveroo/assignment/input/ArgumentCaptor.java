package com.deliveroo.assignment.input;

import com.deliveroo.assignment.exception.ApplicationException;
import com.deliveroo.assignment.constant.ErrorConstants;

import java.util.Objects;

public class ArgumentCaptor {

    public static String captureInput(String[] args) {
        if(Objects.isNull(args) || args.length < 1) {
            throw new ApplicationException(ErrorConstants.ERROR_INVALID_INPUT, ErrorConstants.ERROR_INVALID_INPUT_MSG);
        }
        return args[0];
    }

}
