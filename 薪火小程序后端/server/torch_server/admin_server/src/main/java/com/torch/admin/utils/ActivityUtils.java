package com.torch.admin.utils;

import java.util.Calendar;

public class ActivityUtils {

    public static String generateIdentify(String organizer) {
        String timeInMillis = String.valueOf(Calendar.getInstance().getTimeInMillis());
        if (organizer.length() == 0) {
            return timeInMillis;
        } else {
            return organizer + "-" + timeInMillis.substring(8);
        }
    }

}
