package com.Anime.App.controller;

import com.Anime.App.model.Genre;
import com.Anime.App.request.GenreRequest;
import com.Anime.App.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping("/admin/genre")
    public ResponseEntity<Genre> createGenre(@RequestBody GenreRequest genreRequest){
        Genre genre=genreService.createGenre(genreRequest);
        return  new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @GetMapping("/genre/all")
    public ResponseEntity<List<Genre>> getAllGenres(){
        List<Genre> genreList=genreService.getAllGenres();
        return new ResponseEntity<>(genreList,HttpStatus.OK);
    }

    @GetMapping("genre/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id){
        Genre genre=genreService.getGenreById(id);
        return  new ResponseEntity<>(genre,HttpStatus.OK);
    }

    @DeleteMapping("/admin/genre/{id}")
    public String deleteGenre(@PathVariable Long id){
        return genreService.deleteGenre(id);
    }
}
