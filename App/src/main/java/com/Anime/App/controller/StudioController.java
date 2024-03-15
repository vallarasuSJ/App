package com.Anime.App.controller;

import com.Anime.App.model.Studio;
import com.Anime.App.request.StudioRequest;
import com.Anime.App.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudioController {

    @Autowired
    private StudioService studioService;


    @PostMapping("/admin/studio")
    public ResponseEntity<Studio> createStudio(@RequestBody StudioRequest studioRequest) throws ParseException {
        Studio studio=studioService.createStudio(studioRequest);
        return new ResponseEntity<>(studio, HttpStatus.OK);
    }

    @GetMapping("/studio/all")
    public ResponseEntity<List<Studio>> getAllStudios(){
        List<Studio> studioList=studioService.getAllStudios();
        return new ResponseEntity<>(studioList,HttpStatus.OK);
    }

    @DeleteMapping("/admin/studio/{id}")
    public String deleteStudioById(@PathVariable Long id) {
        return studioService.deleteStudioById(id);
    }

    @GetMapping("/studio/{id}")
    public <T>ResponseEntity<T> getStudioById(@PathVariable Long id){
        T studio=studioService.getStudioById(id);
        return new ResponseEntity<>(studio,HttpStatus.OK);
    }






}
