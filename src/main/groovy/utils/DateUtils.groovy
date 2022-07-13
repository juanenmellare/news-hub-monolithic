package utils

import java.text.SimpleDateFormat
import java.time.Instant

class DateUtils {
    final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss")

    private static String formatInstant(Instant instant) {
        return dateFormat.format(Date.from(instant))
    }

    static Instant getNow() {
        return Instant.now()
    }

    static String getNowString() {
        Instant now = getNow()
        return formatInstant(now)
    }
}
