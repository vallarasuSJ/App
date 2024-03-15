package com.Anime.App.controller;

import com.Anime.App.model.Anime;
import com.Anime.App.request.AnimeRequest;
import com.Anime.App.response.AnimeResponse;
import com.Anime.App.response.ApiResponse;
import com.Anime.App.service.AnimeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    @Autowired
    private ApiResponse apiResponse;


    @PostMapping("/admin/anime")
    public ResponseEntity<Anime> createAnime(@RequestBody AnimeRequest animeRequest) throws ParseException {
        Anime anime=animeService.createAnime(animeRequest);
        return new ResponseEntity<>(anime, HttpStatus.OK);
    }

    @PutMapping("/admin/anime")
    public ResponseEntity<Anime> updateAnime(@RequestBody AnimeRequest animeRequest) throws ParseException {
        Anime anime=animeService.updateAnime(animeRequest);
        return new ResponseEntity<>(anime,HttpStatus.OK);


    }

    @DeleteMapping("/admin/anime/{id}")
    public String deleteAnime(@PathVariable Long id){
        return animeService.deleteMapping(id);
    }

    @GetMapping("/studio/{studioId}/{genreId}")
    public ResponseEntity<List<Anime>> getAnimeByStudioAndGenre(@PathVariable Long studioId, @PathVariable Long genreId){
        List<Anime> animeList=animeService.getAnimeByStudioAndGenre(studioId,genreId);
        return new ResponseEntity<>(animeList,HttpStatus.OK);
    }

    @GetMapping("/anime/all")
    public ResponseEntity<ApiResponse> getAllAnime(){
        List<AnimeResponse>animeList=animeService.getAllAnime();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(animeList);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


}
