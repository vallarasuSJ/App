package com.Anime.App.dto;


import com.Anime.App.model.Anime;
import com.Anime.App.response.AnimeResponse;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnimeDto {
    public List<AnimeResponse> mapToAnime(List<Anime> animeList) {
        List<AnimeResponse> animeResponseList=new ArrayList<>();
        for(Anime anime:animeList){
            AnimeResponse animeResponse=new AnimeResponse();
            animeResponse.setAnimeName(anime.getTitle());
            animeResponse.setId(anime.getId());
            animeResponse.setDirector(anime.getDirector());
            animeResponse.setDescription(anime.getDescription());
            animeResponse.setReleaseDate(anime.getReleaseDate());
            animeResponse.setGenreName(anime.getGenres().getGenreName());
            animeResponse.setStudioName(anime.getStudios().getStudioName());
            animeResponseList.add(animeResponse);
        }
        return animeResponseList;
    }
}
