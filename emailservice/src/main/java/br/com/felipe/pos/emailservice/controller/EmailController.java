package br.com.felipe.pos.emailservice.controller;


import java.util.Properties;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController{

	@RequestMapping("/teste")
	public void run() {
		try {
			ActiveMQConnectionFactory factory =
					new ActiveMQConnectionFactory("tcp://192.168.99.100:61616");
			// Cria a conexão com ActiveMQ
			Connection connection = factory.createConnection();
			// Inicia a conexão
			connection.start();
			// Cria a sessão
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination queue = session.createQueue("Queue");
			MessageConsumer consumer = session.createConsumer(queue);
			Message message = consumer.receive();
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				System.out.println("Consumer Received: " + text);
				EnviarEmail(text);
			}
			session.close();
			connection.close();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}

	}
	public void EnviarEmail(String mensagem){
		Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected javax.mail.PasswordAuthentication getPasswordAuthentication()
                         {
                               return new javax.mail.PasswordAuthentication("Email@gmail.com", "senhaemail");
                         }
                    });

        //session.setDebug(true);

        try {

              javax.mail.Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress("remetente@gmail.com"));

              javax.mail.Address[] toUser = InternetAddress 
                         .parse("destinatario@gmail.com");

              message.setRecipients(javax.mail.Message.RecipientType.TO, toUser);
              message.setSubject("Confirmação da reserva");
              message.setText(mensagem);

              javax.mail.Transport.send(message);

              System.out.println("Email enviado");

         } catch (MessagingException e) {
              throw new RuntimeException(e);
        }
	}
	

}
