package com.Anime.App.service;

import com.Anime.App.exception.IdNotFoundException;
import com.Anime.App.model.Anime;
import com.Anime.App.model.Genre;
import com.Anime.App.model.Studio;
import com.Anime.App.repository.AnimeRepository;
import com.Anime.App.repository.GenreRepository;
import com.Anime.App.repository.StudioRepository;
import com.Anime.App.request.StudioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudioService {

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AnimeRepository animeRepository;

    public Studio createStudio(StudioRequest studioRequest) throws ParseException {
        Studio studio=new Studio();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        Date date1=dateFormat.parse(studioRequest.getFoundedDate());
        studio.setFoundedDate(date1);
        studio.setStudioName(studioRequest.getStudioName());
        List<Genre> genres =new ArrayList<>();
        for(Long i:studioRequest.getGenreId()){
            genres.add(genreRepository.getReferenceById(i));
        }
        studio.setGenres(genres);
        studio.setFounderName(studioRequest.getFounderName());
        studioRepository.save(studio);
        return  studio;
    }

    public List<Studio> getAllStudios() {
        return studioRepository.findAll();
    }

    public String deleteStudioById(Long id) {
        if(studioRepository.findById(id)==null){
            throw new IdNotFoundException("Id not found");
        }
        studioRepository.deleteById(id);
        return "Studio deleted successfully";
    }

    public <T> T getStudioById(Long studioId) {
        if(studioRepository.existsById(studioId)){
            Studio studio=studioRepository.getReferenceById(studioId);
            return (T) studio;
        }
        else{
            throw new IdNotFoundException("Id not found");
        }
    }


}
