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
import java.util.ArrayList;
import java.util.List;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Car;
import model.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Max
 */
public class Sender {
//    private final InitialContext jndiContext;
//    private final ConnectionFactory connectionFactory;
//    private final Destination dest;
//    private final Connection connection;
//    private final Session session;
//    private final MessageProducer producer;
//    private TextMessage message;
    
    public Sender() {
//        jndiContext = new InitialContext();
//        connectionFactory = (ConnectionFactory)jndiContext.lookup("jms/__defaultConnectionFactory");
//        dest = (Destination) jndiContext.lookup("CarSimulator");
//        connection = connectionFactory.createConnection();
//        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        producer = session.createProducer(dest);
    }
    
    public boolean sendPackage(ArrayList<Location> locations, String carName) throws JMSException {
//        try {
////            message = session.createObjectMessage((Serializable) locations.toString());
////            message.setStringProperty("carName", carName);
////            message.setObject(locations);
//            message = session.createTextMessage("Hoi");
//            message.setStringProperty("carName", carName);
//            producer.send(message);
//        } catch (Exception ex) {
//            System.out.println("Message not sent: " + ex.toString());
//            return false;
//        }
//        return true;
        
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.setProperty(ConnectionConfiguration.imqAddressList, "145.93.81.179:7676");

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
                jsArray.add(jloc);
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
