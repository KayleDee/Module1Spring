package com.Spring.module1spring.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository
        extends JpaRepository<Game, Long> {

    //SELECT * FROM game WHERE game = ?;
    @Query("SELECT g FROM Game g WHERE g.gameId = ?1")
    Optional<Game> findById(Long gameId);
}
