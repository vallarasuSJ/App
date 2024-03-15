package com.Anime.App.repository;


import com.Anime.App.model.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetTokenRepository extends JpaRepository<ResetToken,Long> {
    ResetToken findByToken(String token);
}
