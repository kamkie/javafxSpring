package net.devops.javafxspring.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.devops.javafxspring.backend.model.User;
import net.devops.javafxspring.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    ObjectMapper objectMapper;

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

    @RequestMapping(method = RequestMethod.GET, value = "/userListHtml", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity userListHtml() throws Exception {
        List<User> userList = usersRepository.findAll();

        List<Method> setters = Arrays.stream(User.class.getMethods())
                .filter(method -> method.getName().startsWith("get") && !method.getName().startsWith("getClass"))
                .collect(Collectors.toList());

        String header = setters.stream()
                .map(method -> "<th>" + method.getName().substring(3) + "</th>")
                .collect(Collectors.joining());

        String collect = userList.stream()
                .map(user -> setters.stream()
                                .map(method -> {
                                            Object invoke = null;
                                            try {
                                                invoke = method.invoke(user);
                                            } catch (IllegalAccessException | InvocationTargetException e) {
                                                log.error("error", e);
                                            }
                                            return invoke;
                                        }
                                )
                                .map(o -> "<td>" + o + "</td>")
                                .collect(Collectors.joining())
                )
                .map(s -> "<tr>" + s + "</tr>")
                .collect(Collectors.joining());

        return ResponseEntity.ok("<table>" + "<tr>" + header + "</tr>" + collect + "</table>");
    }

}
