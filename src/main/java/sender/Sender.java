/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Car;
import model.Location;

/**
 *
 * @author Max
 */
public class Sender {
    private final InitialContext jndiContext;
    private final ConnectionFactory connectionFactory;
    private final Destination dest;
    private final Connection connection;
    private final Session session;
    private final MessageProducer producer;
    private TextMessage message;
    
    public Sender() throws NamingException, JMSException {
        jndiContext = new InitialContext();
        connectionFactory = (ConnectionFactory)jndiContext.lookup("jms/__defaultConnectionFactory");
        dest = (Destination) jndiContext.lookup("CarSimulator");
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(dest);
    }
    
    public boolean sendPackage(ArrayList<Location> locations, String carName) {
        try {
//            message = session.createObjectMessage((Serializable) locations.toString());
//            message.setStringProperty("carName", carName);
//            message.setObject(locations);
            message = session.createTextMessage("Hoi");
            message.setStringProperty("carName", carName);
            producer.send(message);
        } catch (Exception ex) {
            System.out.println("Message not sent: " + ex.toString());
            return false;
        }
        return true;
    }
}
