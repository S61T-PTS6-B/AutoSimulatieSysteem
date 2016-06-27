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

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public long getDate() {
        return date;
    }

    String latitude;
    String longitude;
    long date;
    
    public Location(String latitude, String longitude) {
        this.date = System.currentTimeMillis();
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
}
