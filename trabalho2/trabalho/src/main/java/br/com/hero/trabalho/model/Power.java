package br.com.hero.trabalho.model;


import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import java.io.Serializable;

@Relation(collectionRelation = "powers")
@Entity
@Table(name="power")
public class Power extends RepresentationModel<Power> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "hero_id")
    private Hero hero;

    @Column(name = "power_name", nullable = false, length = 50)
    private String powerName;

    @Column(name = "strong_point", nullable = false, length = 50)
    private String strongPoint;

    @Column(name = "weak_point", nullable = false, length = 50)
    private String weakPoint;

    public Power() {
    }

    public Power(long id, String powerName, String strongPoint, String weakPoint) {
        this.id = id;
        this.powerName = powerName;
        this.strongPoint = strongPoint;
        this.weakPoint = weakPoint;
        this.hero = hero;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getStrongPoint() {
        return strongPoint;
    }

    public void setStrongPoint(String strongPoint) {
        this.strongPoint = strongPoint;
    }

    public String getWeakPoint() {
        return weakPoint;
    }

    public void setWeakPoint(String weakPoint) {
        this.weakPoint = weakPoint;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
