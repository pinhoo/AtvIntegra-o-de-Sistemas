package br.com.felipe.pos.usuario.controller;

import java.util.List;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.pos.usuario.model.Agenda;
import br.com.felipe.pos.usuario.model.service.AgendaService;
import br.com.felipe.pos.usuario.model.service.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping("/agenda")
public class AgendaController{
	@Autowired
	private UsuarioService userService;

	@Autowired
	private AgendaService agendaService;

	@RequestMapping(value = "/reserva/{idUsuario}", method = RequestMethod.GET)
	public Agenda realizarReserva(@PathVariable int idUsuario) {
		Agenda agenda = new Agenda();
		agenda.setUsuario(userService.listaById(idUsuario));
		agenda.setId(2);
		agendaService.salvarReserva(agenda);
		enviarMensageiro(agenda);
		return agenda;
	}
	@RequestMapping("/reserva")
	public List<Agenda> listarReserva(){
		return agendaService.listarReservas();
	}

	public void enviarMensageiro(Agenda agenda) {
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
		try {

			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.99.100:61616");
			Connection conn = factory.createConnection();
			conn.start();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination queue = session.createQueue("Queue");
			MessageProducer producer = session.createProducer(queue);
			//ObjectMessage objMessage = session.createObjectMessage(user);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			String msg = "Confirmado o usuário "+agenda.getUsuario().getNome()+" de CPF: "+agenda.getUsuario().getCpf();
			TextMessage message = session.createTextMessage(msg);
			
			producer.send(message);
			
			session.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
