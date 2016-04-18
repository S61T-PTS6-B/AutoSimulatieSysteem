/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Max
 */
public interface IService {
    
    public void addCar(String name, String color, String origin, String destination);
    
    public void addLocation(String name, String lat, String lon);
}
