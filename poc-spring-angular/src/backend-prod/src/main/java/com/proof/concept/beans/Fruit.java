package com.proof.concept.beans;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_fruit")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "fruit_id", nullable = false)
    private Integer fruitId;

    @Basic(optional = false)
    @Column(name = "fruit_name", length = 255, nullable = false)
    private String fruitName;

    @Basic(optional = false)
    @Column(name = "fruit_rating", nullable = false)
    private Integer fruitRating;

    public Fruit(String fruitName, Integer fruitRating) {
        this.fruitName = fruitName;
        this.fruitRating = fruitRating;
    }

    public Fruit(){}

    public Fruit(Integer fruitId, String fruitName, Integer fruitRating) {
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.fruitRating = fruitRating;
    }

    public Integer getFruitId() {
        return fruitId;
    }

    public void setFruitId(Integer fruitId) {
        this.fruitId = fruitId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public Integer getFruitRating() {
        return fruitRating;
    }

    public void setFruitRating(Integer fruitRating) {
        this.fruitRating = fruitRating;
    }
}
