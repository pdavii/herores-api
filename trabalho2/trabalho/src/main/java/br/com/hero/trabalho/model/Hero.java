package br.com.hero.trabalho.model;


import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import java.io.Serializable;

@Relation(collectionRelation = "heroes")
@Entity
@Table(name="hero")
public class Hero extends RepresentationModel<Hero> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "person_name", nullable = false, length = 50)
    private String personName;

    @Column(name = "hero_name", nullable = false, length = 50)
    private String heroName;

    @Column(name = "team_name", nullable = false, length = 50)
    private String teamName;

    public Hero() {
    }

    public Hero(long id, String personName, String heroName, String teamName) {
        this.id = id;
        this.personName = personName;
        this.heroName = heroName;
        this.teamName = teamName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
