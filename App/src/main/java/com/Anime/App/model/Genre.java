package com.Anime.App.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genreName", nullable = false,length = 100)
    private String genreName;

    @JsonIgnore
    @OneToMany(mappedBy = "genres",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Anime> animeList;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Studio> studios;


}
