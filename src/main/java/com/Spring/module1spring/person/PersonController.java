package com.Spring.module1spring.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    private final PersonService personService;

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
}
