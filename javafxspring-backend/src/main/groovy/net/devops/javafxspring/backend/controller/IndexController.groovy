package net.devops.javafxspring.backend.controller

import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class IndexController {

    @RequestMapping("/")
    def index() {
        "hello word " + (1..16).findAll {it.mod(2) == 1}.join(", ")
    }
}
