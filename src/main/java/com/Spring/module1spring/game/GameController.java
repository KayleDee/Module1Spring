package com.Spring.module1spring.game;

import com.Spring.module1spring.console.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {

        this.gameService = gameService;
    }

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ConsoleRepository consoleRepository;

    @GetMapping
    public List<Game> getGames() {
        return gameService.getGames();
    }

    @PostMapping
    public void registerNewGame(@RequestBody Game game) {
        gameService.addNewGame(game);
    }

    @DeleteMapping(path = "/{gameId}")
    public void deleteGame(
            @PathVariable("gameId") Long gameId) {
        gameService.deleteGame(gameId);
    }

    @PutMapping(path = "/")
    public void upgradeGame(
            @PathVariable("gameId") Long gameId,
            @PathVariable("gameName") String gameName) {
        gameService.updateGame(gameId, gameName);
    }

    @GetMapping(path = "/{gameId}")
    public Optional<Game> oneGame(
            @PathVariable("gameId") Long gameId) {
        return gameService.oneGame(gameId);
    }



}
