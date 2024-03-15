package com.Spring.module1spring.console;

import com.Spring.module1spring.game.Game;
import com.Spring.module1spring.game.GameRepository;
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
    ConsoleService consoleService;
    @Autowired
    GameService gameService;
    @Autowired
    PersonService personService;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    GameRepository gameRepository;
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

    @PutMapping("/{consoleId}/game/{gameId}")
    Console addNewConsoleToGame(
            @PathVariable Long gameId,
            @PathVariable Long consoleId
    ) {
        Game game = gameRepository.findById(gameId).get();
        Console console = consoleRepository.findById(consoleId).get();
        console.applyGame(game);
        return consoleRepository.save(console);
    }

    @PutMapping("/{consoleId}/person/{personId}")
    Console addNewConsoleToAPerson(
            @PathVariable Long consoleId,
            @PathVariable Long personId

    ) {
        Console console = consoleRepository.findById(consoleId).get();
        Person person = personRepository.findById(personId).get();
        console.applyC(person);
        return consoleRepository.save(console);
    }

}
