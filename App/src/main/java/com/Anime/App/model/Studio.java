package com.Anime.App.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "studios")
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private Date foundedDate;

    @Column(nullable = false,name = "founderName",length = 70,updatable = false)
    private String founderName;

    @Column(name = "studioName", nullable = false, length = 100)
    private String studioName;

    @JsonIgnore
    @OneToMany(mappedBy = "studios",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Anime> animeList;

    @JsonIgnore
    @JoinTable(name = "studio_genre", joinColumns = @JoinColumn(name = "studio_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Genre> genres;



}
