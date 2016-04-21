/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import service.IService;

/**
 *
 * @author Max
 */
@ManagedBean(name = "ControllerBean")
@SessionScoped
public class ControllerBean implements Serializable {

    @EJB
    private IService service;
    
    String carName;
    String location;
    
    public void addCar(String name, String color, String origin, String destination) {
        service.addCar(name, color, origin, destination);
    }
    
    public void addLocation() {
        service.addLocation(carName, location);
    }
    
    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setLocation(String location) {
        this.location = location;

    }

    public String getCarName() {
        return carName;
    }

    public String getLocation() {
        return location;
    }
}
