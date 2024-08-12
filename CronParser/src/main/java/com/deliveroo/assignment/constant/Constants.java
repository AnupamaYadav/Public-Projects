package com.deliveroo.assignment.constant;

import com.deliveroo.assignment.model.CronField;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    private static final CronField.Type[] CRON_PATTERN_PLACEHOLDER_V1 = {
            CronField.Type.MINUTE,
            CronField.Type.HOUR,
            CronField.Type.DAY_OF_MONTH,
            CronField.Type.MONTH,
            CronField.Type.DAY_OF_WEEK,
    };

    private static final Map<String, CronField.Type[]> versionMap = new HashMap<>();
    static {
        versionMap.put("v1", CRON_PATTERN_PLACEHOLDER_V1);
    }

    public static CronField.Type[] getCronPattern(String version) {
        return versionMap.get(version);
    }

    public static final String COMMAND = "command";

    public static final String VERSION_V1 = "v1";

    public static final String STAR = "*";

    public static final String HYPHEN = "-";

    public static final String COMMA = ",";

    public static final String SLASH = "/";

}
