package com.Spring.module1spring.console;

import com.Spring.module1spring.game.Game;
import com.Spring.module1spring.person.Person;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Console {
    @Id
    @SequenceGenerator(
            name = "console_sequence",
            sequenceName = "console_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "console_sequence"
    )
    public Long consoleId;
    @ManyToMany
    @JoinTable(
            name = "gamesCompatible",
            joinColumns = @JoinColumn(name = "console_Id"),
            inverseJoinColumns = @JoinColumn(name = "game_Id")
    )
    private Set<Game> gamesCompatible =new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
    public String consoleName;
    public Integer price;
    public LocalDate release_date;

    public Console(
            String consoleName,
            Integer the_price,
            LocalDate release_date) {

        this.consoleId = consoleId;
        this.consoleName = consoleName;
        this.price = the_price;
        this.release_date = release_date;
    }

    public Console() {
    }

    public Long getConsoleId() {return consoleId;}

    public void setConsoleId(Long consoleId) {this.consoleId = Console.this.consoleId;}

    public String getConsoleName() {return consoleName;}

    public void setConsoleName (String consoleName) { this.consoleName = Console.this.consoleName;}

    public Integer getPrice() {return price;}

    public void setPrice(Integer price) {this.price = price;}


    public LocalDate getRelease_date() {return release_date;}

    public void setRelease_date(LocalDate release_date) {this.release_date = release_date;}

    @Override
    public String toString() {
        return "Game{" +
                "consoleId=" + consoleId +
                ", name=" + consoleName + '\'' +
                ", price=" + price +
                ", release_date" + release_date + '\'' +
                '}';


    }

    public Set<Game> getgamesCompatible() {
        return gamesCompatible;
    }

    public void applyGame(Game game) {
        gamesCompatible.add(game);
    }


    public Person getPerson() {
        return person;
    }
    public void applyP(Person person) {
        this.person = person;
    }

    public void applyC(Person person) {
        this.person = person;
    }


}


