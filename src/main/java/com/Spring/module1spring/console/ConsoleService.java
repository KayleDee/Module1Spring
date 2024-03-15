package com.Spring.module1spring.console;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Component
public class ConsoleService {
    private final ConsoleRepository consoleRepository;

    @Autowired
    public ConsoleService(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }
    public List<Console> getConsoles() {return consoleRepository.findAll();}

    public void addNewConsole(Console console) {
        Optional<Console> consoleOptional = consoleRepository
                .findConsoleByName(console.getConsoleName());
        if (consoleOptional.isPresent()) {
            consoleRepository.save(console);
        }

    }
    public void deleteConsole(Long consoleId) {
        boolean exists = consoleRepository.existsByConsoleId(consoleId);
        if (!exists) {
            throw new IllegalStateException(
                    "the console with the id " + consoleId + " cannot be found.");
        }
        consoleRepository.deleteById(consoleId);
    }
    @Transactional
    public void updateConsole(Long consoleId,
                           String ConsoleName
    ) {
        Console console = consoleRepository.findById(consoleId).orElseThrow(() -> new IllegalStateException(
                "The game with the id " + consoleId + " cannont be found."));

        if (ConsoleName != null &&
                ConsoleName.length() > 0 &&
                !Objects.equals(console.getConsoleName(), ConsoleName)) {
            console.setConsoleName(ConsoleName);
        }
        else {
            throw new IllegalStateException("Sorry, but this name is already catalogued. :(");
        }


    }
    public Optional<Console> oneConsole(Long consoleId) {
        return consoleRepository.findById(consoleId);
    }
}
