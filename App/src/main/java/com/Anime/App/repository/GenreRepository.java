package com.Anime.App.repository;

import com.Anime.App.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

    Genre findByGenreName(String genreName);
}
