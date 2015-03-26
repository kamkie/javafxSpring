package net.devops.javafxspring.backend.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import lombok.extern.slf4j.Slf4j;
import net.devops.javafxspring.common.model.User;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Slf4j
public class UserTest {

    private ObjectMapper objectMapper;
    private ObjectWriter objectWriter;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());
        objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
    }

    @Test
    public void testSerialize() throws Exception {
        User user = User.builder()
                .id(1L)
                .login("kamkie")
                .firstName("Kamil")
                .lastName("Kiewisz")
                .socialNumber(123423L)
                .role("Admin")
                .isAdmin(true)
                .isActive(true)
                .created(LocalDateTime.now(Clock.systemUTC()))
                .createdZone(ZonedDateTime.now(Clock.systemUTC()))
                .build();

        String userAsString = objectWriter.writeValueAsString(user);

        log.info(userAsString);

        User userReadValue = objectMapper.readValue(userAsString, User.class);

        log.info(userReadValue.toString());
    }
}
