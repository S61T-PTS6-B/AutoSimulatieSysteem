/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.Queue;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Car;
import model.Location;
import org.json.*;

/**
 *
 * @author Max
 */
public class Sender {
    
    public Sender() {
    }
    
    public boolean sendPackage(ArrayList<Location> locations, String carName) throws JMSException {

        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.setProperty(ConnectionConfiguration.imqAddressList, "192.168.24.222:7676");

        Queue myQueue = new Queue("queuevp");

        try (Connection connection = connFactory.createConnection(); 
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); 
                MessageProducer producer = session.createProducer(myQueue)) {

            Message message = session.createTextMessage("this is my test message");
            message.setStringProperty("carName", carName);
            
            JSONObject container = new JSONObject();
            JSONArray jsArray = new JSONArray();
            for(Location loc : locations) {
                JSONObject jloc = new JSONObject();
                jloc.put("lat", loc.getLatitude());
                jloc.put("long", loc.getLongitude());
                jloc.put("date", loc.getDate());
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(loc.getDate());
                jsArray.put(jloc);
            }
            container.put("locations", jsArray);
            message.setStringProperty("locations", container.toString());
            producer.send(message);
        }
        catch (Exception ex) {
            System.out.println("Message not sent: " + ex.toString());
            return false;
        }
        return true;
    }
}
