/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max
 */
public class Car {
    
    List<Location> locations;
    String name;
    String color;
    String origin;
    String destination;
    
    public Car() {
        this.locations = new ArrayList<>();
    }

    public Car(String name, String color, String origin, String destination) {
        this.locations = new ArrayList<>();
        this.name = name;
        this.color = color;
        this.origin = origin;
        this.destination = destination;
    }
    
    
}
