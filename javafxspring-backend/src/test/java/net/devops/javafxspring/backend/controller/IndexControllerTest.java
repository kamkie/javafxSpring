package net.devops.javafxspring.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;

@Slf4j
public class IndexControllerTest {

    @Test
    public void testIndex() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Entity something = new Entity(1, "something", new Date());

        for (int j = 0; j < 1000; j++) {
            long startTime = System.nanoTime();
            long startTimeMillis = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                objectMapper.writeValueAsString(something);
            }
            long stopTime = System.nanoTime();
            long stopTimeMillis = System.currentTimeMillis();
            log.info("time spent s{}ms {}n", stopTimeMillis - startTimeMillis, stopTime - startTime);
        }
    }

    @Data
    @AllArgsConstructor
    private class Entity {
        private int index;
        private String value;
        Date created;
    }
}