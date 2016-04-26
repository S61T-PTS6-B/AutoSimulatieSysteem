/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
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
    public String addLocation(String carName, String location) {
        try {
            File file = new File("C:/temp/carSimulatorLoggingString.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.write(Paths.get("C:/temp/carSimulatorLoggingString.txt"), (carName + ": " + location + ", " + new Date() + "\r\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        for (Car c : cars) {
            if (c.getName().equals(carName)) {
                location = location.replace("(", "").replace(")", "");
                String[] locationSplit = location.split(", ");
                Location theLocation = new Location(locationSplit[0], locationSplit[1]);
                c.addLocation(theLocation);
            }
        }
        return carName + ": " + location;
    }
}
