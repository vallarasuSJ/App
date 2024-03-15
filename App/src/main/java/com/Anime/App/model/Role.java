package com.Anime.App.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    public static final String USER="USER";
    public static final String ADMIN="ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,length = 20)
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "roles",cascade = CascadeType.ALL)
    private List<AppUser>appUsers;







}
