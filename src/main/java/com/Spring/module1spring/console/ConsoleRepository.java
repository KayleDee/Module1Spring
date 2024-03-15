package com.Spring.module1spring.console;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsoleRepository
    extends JpaRepository<Console, Long> {

    //SELECT * FROM person WHERE email = ?;
    @Query("SELECT c FROM Console c WHERE c.consoleName = ?1")
    Optional<Console> findConsoleByName(String ConsoleName);

}
