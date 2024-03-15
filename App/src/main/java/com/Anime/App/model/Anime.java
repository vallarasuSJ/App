package com.Anime.App.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="anime")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "title ",nullable = false,length = 100)
    private String title;

    @Column(name = "director",nullable = false,length = 50)
    private String director;

    @Column(name = "description",nullable = false,length = 150)
    private String Description;

    @Column(updatable = false)
    private Date releaseDate;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studio_id",referencedColumnName = "id")
    private Studio studios;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id",referencedColumnName = "id")
    private  Genre genres;

}
