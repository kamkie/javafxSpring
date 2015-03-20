package net.devops.javafxspring.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String login;
    private String firstName;
    private String secondName;
    private String lastName;
    private String role;
    private Long socialNumber;

}
