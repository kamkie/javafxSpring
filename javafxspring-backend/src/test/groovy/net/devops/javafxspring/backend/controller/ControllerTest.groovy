package net.devops.javafxspring.backend.controller

import groovy.json.JsonOutput

class ControllerTest extends GroovyTestCase {
    void testJson() {

        Entity something = new Entity(1, "something", new Date());

        JsonOutput jsonOutput = new JsonOutput();

        for (j in 0..1000) {
            long startTime = System.nanoTime();
            long startTimeMillis = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                JsonOutput.toJson(something)
            }
            long stopTime = System.nanoTime();
            long stopTimeMillis = System.currentTimeMillis();
            log.info("time spent ${stopTimeMillis - startTimeMillis}ms ${stopTime - startTime}n");
        }
    }

    class Entity {
        private int index;
        private String value;
        Date created;

        Entity(int index, String value, Date created) {
            this.index = index
            this.value = value
            this.created = created
        }
    }
}
