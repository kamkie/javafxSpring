package net.devops.javafxspring.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.devops.javafxspring.backend.converter.LocalDateTimePersistenceConverter;
import net.devops.javafxspring.backend.converter.ZonedDateTimePersistenceConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = true)
    private String secondName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Long socialNumber;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private boolean isAdmin;

    @Column(nullable = true)
    private Long activationHash;

    @Column(nullable = false)
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime created;

    @Column(nullable = false)
    @Convert(converter = ZonedDateTimePersistenceConverter.class)
    private ZonedDateTime createdZone;

}
