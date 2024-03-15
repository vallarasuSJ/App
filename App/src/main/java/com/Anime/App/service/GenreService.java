package com.Anime.App.service;

import com.Anime.App.exception.IdNotFoundException;
import com.Anime.App.model.Genre;
import com.Anime.App.model.Studio;
import com.Anime.App.repository.GenreRepository;
import com.Anime.App.repository.StudioRepository;
import com.Anime.App.request.GenreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private StudioRepository studioRepository;

    public Genre createGenre(GenreRequest genreRequest) {
        Genre genre=new Genre();
        if(genreRepository.findByGenreName(genreRequest.getGenreName())==null){
            genre.setGenreName(genreRequest.getGenreName());
            List<Studio> studioList =new ArrayList<>();
            for(Long i:genreRequest.getStudioListId()){
                studioList.add(studioRepository.getReferenceById(i));
            }
            genre.setStudios(studioList);
            genreRepository.save(genre);
            return genre;
        }
        throw new IdNotFoundException("Id not found");
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public String deleteGenre(Long id) {
        genreRepository.findById(id).orElseThrow(()->new IdNotFoundException("Genre id not found"));
        genreRepository.deleteById(id);
        return "Genre deleted successfully";
    }

    public Genre getGenreById(Long id) {
        if(genreRepository.findById(id)==null) {
            throw new IdNotFoundException("Id not found");
        }
        return genreRepository.getReferenceById(id);
    }
}
