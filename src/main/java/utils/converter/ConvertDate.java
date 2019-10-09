package utils.converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ConvertDate {

    public static LocalDateTime convertTimestampToLocalDateTime(Timestamp date){
        return date.toLocalDateTime();
    }

    public static Timestamp convertLocalDateTimeToTimestamp(LocalDateTime dateTime){
        return Timestamp.valueOf(dateTime);
    }
}
