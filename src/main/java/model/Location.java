/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Max
 */
public class Location implements Serializable {

    String longitude;
    String latitude;
    Date date;
    
    public Location(String longitude, String latitude) {
        this.date = new Date();
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
}
