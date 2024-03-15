package com.Anime.App.request;

import com.Anime.App.model.Studio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreRequest {
    private Long id;

    private String genreName;

    private List<Long> studioListId;
}
