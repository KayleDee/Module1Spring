package com.Spring.module1spring.person;

import com.Spring.module1spring.console.Console;
import com.Spring.module1spring.console.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    private final PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ConsoleRepository consoleRepository;
    @Autowired
    public PersonController(PersonService personService) {

        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @PostMapping
    public void registerNewPerson(@RequestBody Person person) {
        personService.addNewPerson(person);
    }

    @DeleteMapping(path = "{personId}")
    public void deletePerson(
            @PathVariable("personId") Long personId) {
        personService.deletePerson(personId);
    }

    @PutMapping(path = "{personId}")
    public void upgradePerson(
            @PathVariable("personId") Long personId,
            @RequestParam(required = false) String phoneNum,
            @RequestParam(required = false) String email) {
        personService.updatePerson(personId, phoneNum, email);
    }

    @GetMapping(path = "{personId}")
    public Optional<Person> onePerson(
            @PathVariable("personId") Long personId) {
       return personService.onePerson(personId);
    }


    @PutMapping("/{consoleId}/person/{personId}")
    Console addNewConsoleToAPerson(
            @PathVariable Long consoleId,
            @PathVariable Long personId

    ) {
        Console console = consoleRepository.findById(consoleId).get();
        Person person = personRepository.findById(personId).get();
        console.applyP(person);
        return consoleRepository.save(console);
    }
}
