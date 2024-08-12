package com.deliveroo.assignment.service;

import com.deliveroo.assignment.constant.Constants;

import java.util.ArrayList;
import java.util.List;

public class BaseCronParser {

    public List<String> parseExpression(String part, int minimumValue, int maximumValue) {
        List<String> result = new ArrayList<>();

        if (part.equals(Constants.STAR)) {
            for (int i = minimumValue; i <= maximumValue; i++) {
                result.add(String.valueOf(i));
            }
        } else if (part.contains(Constants.COMMA)) {
            for (String s : part.split(Constants.COMMA)) {
                result.addAll(parseExpression(s, minimumValue, maximumValue));
            }
        } else if (part.contains(Constants.HYPHEN)) {
            String[] range = part.split(Constants.HYPHEN);
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);
            for (int i = start; i <= end; i++) {
                result.add(String.valueOf(i));
            }
        } else if (part.contains(Constants.SLASH)) {
            String[] step = part.split(Constants.SLASH);
            int base = part.startsWith(Constants.STAR) ? minimumValue : Integer.parseInt(step[0]);
            int increment = Integer.parseInt(step[1]);
            for (int i = base; i <= maximumValue; i += increment) {
                result.add(String.valueOf(i));
            }
        } else {
            result.add(part);
        }
        return result;
    }
}
