package com.ecommerce.springtest;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9._-]+$")
    private String username;

    @NotBlank
    @Size(min = 8)
    @Column(nullable = false)
    private String password;
}
