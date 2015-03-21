package net.devops.javafxspring.backend.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Converter
public class ZonedDateTimePersistenceConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime entityValue) {
        if (entityValue != null) {
            return Timestamp.valueOf(entityValue.toLocalDateTime());
        }
        return null;
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp databaseValue) {
        if (databaseValue != null) {
            return databaseValue.toLocalDateTime().atZone(ZoneId.systemDefault());
        }
        return null;
    }
}