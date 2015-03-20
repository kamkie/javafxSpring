package net.devops.javafxspring.backend.controller;

import net.devops.javafxspring.backend.model.User;
import net.devops.javafxspring.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("hello word");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public ResponseEntity<User> user() {
        return ResponseEntity.ok(User.builder()
                .id(1L)
                .firstName("Kamil")
                .lastName("Kiewisz")
                .socialNumber(123L)
                .build());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/userList")
    public ResponseEntity<List<User>> userList() {
        return ResponseEntity.ok(usersRepository.findAll());
    }
}
