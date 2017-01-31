package br.com.felipe.pos.usuario.model.service;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements Runnable {

	@Override
	public void run() {
		try {
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.99.100:61616");
			Connection conn = factory.createConnection();
			conn.start();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination queue = session.createQueue("Queue");
			MessageProducer producer = session.createProducer(queue);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			String msg = "ActiveMQ Ativo Recebendo as Mensagens";
			
			TextMessage message = session.createTextMessage(msg);
			producer.send(message);
			
			session.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error");
		}

	}

}
