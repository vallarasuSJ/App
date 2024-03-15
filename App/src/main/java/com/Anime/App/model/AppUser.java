package com.Anime.App.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="appUsers")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", unique = true,nullable=false, length = 100)
    private String username;

    @JsonIgnore
    @Column(name = "password" , nullable = false, length = 100)
    private String password;

    private String email;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role roles;

    @JsonIgnore
    @OneToOne(mappedBy = "appUser")
    private ResetToken resetToken;

}
