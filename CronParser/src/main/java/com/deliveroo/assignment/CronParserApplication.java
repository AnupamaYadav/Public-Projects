package com.deliveroo.assignment;

import com.deliveroo.assignment.constant.Constants;
import com.deliveroo.assignment.input.ArgumentCaptor;
import com.deliveroo.assignment.service.CronService;

public class CronParserApplication {

    public static void main(String[] args) {

        String input = ArgumentCaptor.captureInput(args);
        CronService cronService = new CronService();
        /*
            We consider the initial version of cron parser application as v1.
         */
        cronService.handle(input, Constants.VERSION_V1);

    }
}