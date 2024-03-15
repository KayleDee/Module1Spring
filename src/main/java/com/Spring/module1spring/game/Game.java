package com.Spring.module1spring.game;

import com.Spring.module1spring.console.Console;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Game {


    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence")
    public Long gameId;
    @JsonIgnore
    @ManyToMany(mappedBy = "gamesCompatible")
    public Set<Console> compatibleConsole = new HashSet<>();

    public Long getGameId() {
        return gameId;
    }
    public String gameName;
    public Integer price;
    public LocalDate release_date;
    public Game(String gameName, Integer price, LocalDate release_date) {
        this.gameName = gameName;
        this.price = price;
        this.release_date = release_date;
    }
    public Game() {
    }

    public Game(Long gameId, String gameName, Integer price, LocalDate release_date) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.price = price;
        this.release_date = release_date;
    }


    public String getGameName() {return gameName;}

    public void setGameName (String gameName) { this.gameName = gameName;}

    public Integer getPrice() {return price;}

    public void setPrice(Integer price) {this.price = price;}


    public LocalDate getRelease_date() {return release_date;}

    public void setRelease_date(LocalDate release_date) {this.release_date = release_date;}

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", name=" + gameName + '\'' +
                ", price=" + price +
                ", release_date" + release_date + '\'' +
                '}';


    }
    public Set<Console> getCompatibleConsole() {
        return compatibleConsole;
    }
}



