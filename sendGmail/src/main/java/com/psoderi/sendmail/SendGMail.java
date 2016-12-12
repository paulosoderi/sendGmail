package com.psoderi.sendmail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/* caso de uma exception de autenticacao, verifique se o email e senha estao corretos, 
	caso esteja, devera liberar nas configuracoes de email do seu GMAIL a opcao de acesso a aplicacoes nao seguras atraves do link abaixo:
	https://www.google.com/settings/security/lesssecureapps
	Se o problema persistir, devera chamar o link abaixo:
	https://accounts.google.com/displayunlockcaptcha
	*/

public class SendGMail {	
 
	public static void main(String args[]) throws AddressException, MessagingException {
		Properties propriedadesGmail;
		Session session;
		MimeMessage mensagem;
		// Propriedades do GMAIL
		propriedadesGmail = System.getProperties();
		propriedadesGmail.put("mail.smtp.port", "587");
		propriedadesGmail.put("mail.smtp.auth", "true");
		propriedadesGmail.put("mail.smtp.starttls.enable", "true");
 
		// Abrindo uma sessao com o GMAIL
		session = Session.getDefaultInstance(propriedadesGmail, null);
		mensagem = new MimeMessage(session);
		
		//Destinatario, caso queira enviar com copia oculta, chame o metodo addRecipient passando a constante CC ao inves de TO
		mensagem.addRecipient(Message.RecipientType.TO, new InternetAddress("teste@teste.com"));
		//Titulo do email
		mensagem.setSubject("teste titulo email");
		
		//corpo do email
		String emailBody = "teste corpo email<br>att<br>Psoderi";
		mensagem.setContent(emailBody, "text/html");
		
		
		// Envio do email
		Transport t = session.getTransport("smtp");
		
		//Lembr-se de alterar email e senha
		t.connect("smtp.gmail.com", "seu_email@gmail.com", "suasenhadoseuemail");
		t.sendMessage(mensagem, mensagem.getAllRecipients());
		t.close();
		System.out.println("Email enviado com sucesso");
	}
}
