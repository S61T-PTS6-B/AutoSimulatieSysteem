/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;
import model.Car;
import model.Location;

/**
 *
 * @author Max
 */
@Stateless
public class Service implements IService {

    List<Car> cars = new ArrayList<>();
    
    @Override
    public void addCar(String name, String color, String origin, String destination) {
        Car newCar;
        try {
            newCar = new Car(name, color, origin, destination);
            cars.add(newCar);
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(newCar, 0, 5000);

        } catch (NamingException | JMSException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error adding the car: " + ex.getStackTrace());
        }
    }

    @Override
    public void addLocation(String carName, String location) {

        for (Car c : cars) {
            if (c.getName().equals(carName)) {
                location = location.replace("(", "").replace(")", "");
                String[] locationSplit = location.split(", ");
                Location theLocation = new Location(locationSplit[0], locationSplit[1]);
                c.addLocation(theLocation);
            }
        }
        //System.out.print(carName + ": " + location);
    }
}
