package com.Anime.App.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimeResponse {

    private Long id;

    private String animeName;

    private String director;

    private String description;

    private Date releaseDate;

    private String studioName;

    private String genreName;
}
