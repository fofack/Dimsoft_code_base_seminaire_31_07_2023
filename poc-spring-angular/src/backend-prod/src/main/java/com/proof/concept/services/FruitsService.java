package com.proof.concept.services;

import com.proof.concept.beans.Fruit;

public interface FruitsService {

    public Fruit findByFruitName(String fruitName);

    public Fruit createOrUpdateFruit(Fruit fruit);
    
}
