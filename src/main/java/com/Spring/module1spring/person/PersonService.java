package com.Spring.module1spring.person;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public List<Person> getPersons() {return personRepository.findAll();}

    public void addNewPerson(Person person) {
        Optional<Person> personOptional = personRepository
                .findPersonByEmail(person.getEmail());
        if (personOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        personRepository.save(person);
    }
    public void deletePerson(Long personId) {
        boolean exists = personRepository.existsById(personId);
        if (!exists) {
            throw new IllegalStateException(
                    "the person with the id " + personId + " cannot be found.");
        }
        personRepository.deleteById(personId);
    }
    @Transactional
    public void updatePerson(Long personId,
                             String phoneNum,
                             String email) {
        Person person = personRepository.findById(personId).orElseThrow(() -> new IllegalStateException(
                "The person with the id " + personId + " cannont be found."));

        if (phoneNum != null &&
                phoneNum.length() > 0 &&
                !Objects.equals(person.getPhoneNum(), phoneNum)) {
            person.setPhoneNum(phoneNum);
        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(person.getEmail(), email)) {
            Optional<Person> personOptional = personRepository.findPersonByEmail(email);
            if (personOptional.isPresent()) {
                throw new IllegalStateException("Sorry, but this email is already being used.");
            }
            person.setEmail(email);

        }
    }
    public Optional<Person> onePerson(Long personId) {
        boolean exists = personRepository.existsById(personId);
        if (!exists) {
            throw new IllegalStateException(
                    "Cannot find the person with the Id number: " + personId + ".");
        }
        return personRepository.findById(personId);
    }
}
