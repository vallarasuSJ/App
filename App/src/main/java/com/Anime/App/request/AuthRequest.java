package com.Anime.App.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private Long id;

    private String username;

    private String password;

    private String role;

    private String email;

    private String token;
}
