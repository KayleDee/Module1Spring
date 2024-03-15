package com.Spring.module1spring.console;

import com.Spring.module1spring.game.Game;
import com.Spring.module1spring.game.GameService;
import com.Spring.module1spring.person.Person;
import com.Spring.module1spring.person.PersonRepository;
import com.Spring.module1spring.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/console")
public class ConsoleController {
    @Autowired
    private ConsoleService consoleService;
    @Autowired
    private GameService gameService;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    public ConsoleController(ConsoleService consoleService,
                             GameService gameService) {

        this.consoleService = consoleService;
        this.gameService = gameService;
    }



    @Autowired
    ConsoleRepository consoleRepository;

    @GetMapping
    public List<Console> getConsoles() {
        return consoleService.getConsoles();
    }

    @PostMapping
    public void registerNewConsole(@RequestBody Console console) {
        consoleService.addNewConsole(console);
    }

    @DeleteMapping(path = "{consoleId}")
    public void deleteConsole(
            @PathVariable("consoleId") Long consoleId) {
        consoleService.deleteConsole(consoleId);
    }

    @PutMapping(path = "{consoleId}")
    public void upgradeConsole(
            @PathVariable("consoleId") Long consoleId,
            @RequestParam(required = false) String gameName) {
        consoleService.updateConsole(consoleId, gameName);
    }

    @GetMapping(path = "{consoleId}")
    public Optional<Console> oneConsole(
            @PathVariable("consoleId") Long consoleId) {
        return consoleService.oneConsole(consoleId);
    }

    @PutMapping("/{gameId}/console/{consoleId}")
    Console addNewConsoleToGame(
            @PathVariable Long gameId,
            @PathVariable Long consoleId
    ) {
        Game game = gameService.oneGame(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        Console console = consoleRepository.findById(consoleId).orElseThrow(() -> new RuntimeException("Console not found"));
        console.applyGame(game);
        return consoleRepository.save(console);
    }


}
