package com.mycompany.objectdb;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Essa é uma somente uma classe de teste comum que representa um ponto em um plano cartesiano
@Entity
public class Point {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
     Long id;
    // Persistent Fields:
     int x;
     int y;

    // Constructor:
    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Accessor Methods:
    public int getX() { return this.x; }
    public int getY() { return this.y; }

    // String Representation:
    @Override
    public String toString() {
        return String.format("(%d, %d)", this.x, this.y);
    }
}