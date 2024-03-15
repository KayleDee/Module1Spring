package com.Spring.module1spring.game;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.*;



@Service
@Component
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    public List<Game> getGames() {return gameRepository.findAll();}

    public void addNewGame(Game game) {
        gameRepository.save(game);
    }
    public void deleteGame(Long gameId) {
        boolean exists = gameRepository.existsById(gameId);
        if (!exists) {
            throw new IllegalStateException(
                    "the game with the id " + gameId + " cannot be found.");
        }
        gameRepository.deleteById(gameId);
    }
    @Transactional
    public void updateGame(Long gameId,
                           String gameName
    ) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalStateException(
                "The game with the id " + gameId + " cannont be found."));

        if (gameName != null &&
                gameName.length() > 0 &&
                !Objects.equals(game.getGameName(), gameName)) {
            game.setGameName(gameName);
        }
        else {
            throw new IllegalStateException("Sorry, but this name is already catalogued. :(");
        }


        }

    public Optional<Game> oneGame(Long gameId) {
        boolean exists = gameRepository.existsById(gameId);
        if (!exists) {
            throw new IllegalStateException(
                    "Cannot find the game with the Id number: " + gameId + ".");
        }
        return gameRepository.findById(gameId);
    }
    }


