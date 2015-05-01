package net.devops.javafxspring.backend.controller

import groovy.util.logging.Slf4j
import net.devops.javafxspring.backend.annotation.Loggable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class IndexController {

    byte[] bytes = [65, 65, 65, 65, 65] as byte[]

    @Loggable
    @RequestMapping("/")
    def index() {
        "hello word " + (1..16).findAll { it.mod(2) == 1 }.join(", ")
    }

    @Loggable
    @RequestMapping("/raw")
    def raw() {
        bytes
    }
}
