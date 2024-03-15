package com.Anime.App.service;

import com.Anime.App.dto.AnimeDto;
import com.Anime.App.exception.IdNotFoundException;
import com.Anime.App.model.Anime;
import com.Anime.App.model.Genre;
import com.Anime.App.model.Studio;
import com.Anime.App.repository.AnimeRepository;
import com.Anime.App.repository.GenreRepository;
import com.Anime.App.repository.StudioRepository;
import com.Anime.App.request.AnimeRequest;
import com.Anime.App.response.AnimeResponse;
import com.Anime.App.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class AnimeService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private AnimeDto animeDto;

    public Anime createAnime(AnimeRequest animeRequest) throws ParseException {
        Anime anime=new Anime();
        Genre genre=genreRepository.findById(animeRequest.getGenreId()).orElseThrow(()->new IdNotFoundException("Id not found"));
        anime.setGenres(genre);
        Studio studio=studioRepository.findById(animeRequest.getStudioId()).orElseThrow(()->new IdNotFoundException("Id not found"));
        anime.setStudios(studio);
        anime.setTitle(animeRequest.getTitle());
        anime.setDirector(animeRequest.getDirector());
        anime.setDescription(animeRequest.getDescription());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        Date date=simpleDateFormat.parse(animeRequest.getReleaseDate());
        anime.setReleaseDate(date);
        System.out.println(anime.getReleaseDate());
        animeRepository.save(anime);
        return anime;
    }

    public String deleteMapping(Long id) {
        animeRepository.findById(id).orElseThrow(()->new IdNotFoundException("Id not found"));
        animeRepository.deleteById(id);
        return "Anime deleted successfully";
    }

    public Anime updateAnime(AnimeRequest animeRequest) throws ParseException {
       Anime anime=animeRepository.findById(animeRequest.getId()).orElseThrow(()->new IdNotFoundException("Id not found"));
       anime.setTitle(animeRequest.getTitle());
       anime.setDirector(animeRequest.getDirector());
       anime.setDescription(animeRequest.getDescription());
       SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
       Date date=simpleDateFormat.parse(animeRequest.getReleaseDate());
       anime.setReleaseDate(date);
       animeRepository.save(anime);
       return anime;
    }

    public List<Anime> getAnimeByStudioAndGenre(Long studioId, Long genreId) {
        List<Anime> animeList=animeRepository.findAllAnimeByStudio(studioId,genreId);
        return  animeList;
    }

    public List<AnimeResponse> getAllAnime() {
        List<Anime> animeList=animeRepository.findAll();
        return animeDto.mapToAnime(animeList);
    }
}
