/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import model.Car;

/**
 *
 * @author Max
 */
@Stateless
public class Service implements IService {

    List<Car> cars = new ArrayList<Car>();
    
    @Override
    public void addCar(String name, String color, String origin, String destination) {
        Car newCar = new Car(name, color, origin, destination);
        cars.add(newCar);
    }

    @Override
    public void addLocation(String name, String lat, String lon) {
        
    }
    
}
