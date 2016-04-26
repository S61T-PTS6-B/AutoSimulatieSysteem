/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.swing.Timer;
import sender.Sender;

/**
 *
 * @author Max
 */
public class Car extends TimerTask {

    Sender sender;
    List<Location> locations;
    String name;
    String color;
    String origin;
    String destination;

    public Car() throws NamingException, JMSException {
        this.locations = new ArrayList<>();
        sender = new Sender();
    }

    public Car(String name, String color, String origin, String destination) throws NamingException, JMSException {
        this.locations = new ArrayList<>();
        this.name = name;
        this.color = color;
        this.origin = origin;
        this.destination = destination;
        sender = new Sender();
    }

    @Override
    public void run() {
        if (locations.size() >= 20) {
            ArrayList locationsToSend = new ArrayList<>();
            //If the list is longer than 200, limit the package to 200
            if (locations.size() > 200) {
                locationsToSend = new ArrayList<>(locations.subList(0, 200));
            }
            else {
                locationsToSend = new ArrayList<>(locations.subList(0, locations.size()));                
            }
            boolean succes = sender.sendPackage(locationsToSend, this.name);
            if (succes) {
                //If the list contained less than 200 items, the entire list was sent in the package, so we can start with an empty list again.
                if (locations.size() <= 200) {
                    locations = new ArrayList<>();
                } 
                //If the list contained more than 200 items, the remaining items, that are not sent, should stay in the list. The rest is deleted.
                else {
                    locations = locations.subList(200, locations.size());
                }
            } else {
                //PACKAGE NOT SENT!
            }
        }
    }
    
    public void addLocation(Location location) {
        this.locations.add(location);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
