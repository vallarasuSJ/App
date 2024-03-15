package com.Anime.App.repository;

import com.Anime.App.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime,Long> {

    @Query("SELECT s FROM Anime s WHERE s.studios.id =:studioId AND s.genres.id =:genreId")
    List<Anime> findAllAnimeByStudio(Long studioId,Long genreId);
}
