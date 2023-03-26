package utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.temporal.ChronoUnit


class DateUtils {
    final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss")

    private static String formatInstant(Instant instant) {
        return dateFormat.format(Date.from(instant))
    }

    static Instant getNow() {
        return Instant.now()
    }

    static Instant getNowPlusHours(int hoursToAdd) {
        return getNow().plus(hoursToAdd, ChronoUnit.HOURS)
    }

    static String getNowString() {
        Instant now = getNow()
        return formatInstant(now)
    }
}
