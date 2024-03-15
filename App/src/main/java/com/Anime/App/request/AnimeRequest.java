package com.Anime.App.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimeRequest {

    private Long id;

    private String title;

    private String director;

    private String description;

    private String releaseDate;

    private Long studioId;

    private Long genreId;
}
