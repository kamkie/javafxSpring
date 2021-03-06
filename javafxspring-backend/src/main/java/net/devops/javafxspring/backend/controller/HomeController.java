package net.devops.javafxspring.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.devops.javafxspring.backend.annotation.Loggable;
import net.devops.javafxspring.backend.repository.UsersRepository;
import net.devops.javafxspring.backend.util.HtmlUtil;
import net.devops.javafxspring.common.model.User;
import net.devops.javafxspring.common.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    ObjectMapper objectMapper;

    private final static String stylePadding = " style=\"padding: 10px;\"";

    @Autowired
    private UsersRepository usersRepository;

    private final List<Field> fields = ReflectionUtil.getFields(User.class);

    @Loggable
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("hello word");
    }

    @Loggable
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public ResponseEntity<User> user() {
        return ResponseEntity.ok(User.builder()
                .id(1L)
                .firstName("Kamil")
                .lastName("Kiewisz")
                .socialNumber(123L)
                .build());
    }

    @Loggable
    @RequestMapping(method = RequestMethod.GET, value = "/userList")
    public ResponseEntity<List<User>> userList() {
        return ResponseEntity.ok(usersRepository.findAll());
    }

    @Loggable
    @RequestMapping(method = RequestMethod.GET, value = "/text")
    public void text(HttpServletResponse response) throws Exception {
        response.setHeader(HttpHeaders.CONTENT_TYPE, "text/event-stream");
        PrintWriter writer = response.getWriter();
        for (int i = 0; i < 1000; i++) {
            log.info("loop: {}", i);
            writer.write("--------------------------------------------------------------------------------------------------------------------------------------\n");
            writer.write("--------------------------------------------------------------------------------------------------------------------------------------\n");
            writer.write("--------------------------------------------------------------------------------------------------------------------------------------\n");
            writer.write("--------------------------------------------------------------------------------------------------------------------------------------\n");
            writer.write("--------------------------------------------------------------------------------------------------------------------------------------\n");
            writer.write("--------------------------------------------------------------------------------------------------------------------------------------\n");
            writer.write(i + "\n");
            writer.flush();
            if (writer.checkError()) {
                writer.close();
                return;
            }
            Thread.sleep(1000);
        }
    }

    @Loggable
    @RequestMapping(method = RequestMethod.GET, value = "/userListHtml", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity userListHtml() throws Exception {
        List<User> userList = usersRepository.findAll();

        String header = fields.stream()
                .map(field -> "<th" + stylePadding + ">" + HtmlUtil.htmlEscapeNullSafe(field.getName()) + "</th>")
                .collect(Collectors.joining());

        String collect = userList.stream()
                .map(user -> fields.stream()
                        .map(field -> ReflectionUtil.getFieldValueSafe(field, user))
                        .map(o -> "<td" + stylePadding + ">" + HtmlUtil.htmlEscapeNullSafe(o) + "</td>")
                        .collect(Collectors.joining()))
                .map(s -> "<tr>" + s + "</tr>")
                .collect(Collectors.joining());

        return ResponseEntity.ok("<!DOCTYPE html><body><table border=\"1\" style=\"\">" + "<tr>" + header + "</tr>" + collect + "</table></body>");
    }

}
