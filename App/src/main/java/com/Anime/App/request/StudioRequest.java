package com.Anime.App.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudioRequest {
    private Long id;

    private String foundedDate;

    private String founderName;

    private String studioName;

    private List<Long> genreId;
}
