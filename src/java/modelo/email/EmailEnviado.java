/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.email;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Sony
 */
public class EmailEnviado {

    public static void enviarEmail() {
        final String fromEmail = "ontimebytime5@gmail.com"; //requires valid gmail id
        final String password = "Qwerty321!"; // correct password for gmail id
        final String toEmail = "leoomoreira@virtual.ufc.br"; // can be any email id

        System.out.println("SSLEmail Start");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Session created");

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ontimebytime5@gmail.com"));
            //Remetente

            // Destinatário
            Address[] toUser = InternetAddress.parse("leoomoreira@virtual.ufc.br");

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Enviei email com JavaMail, tinha dúvida, mas já resolvi");//Assunto
            message.setText("Professor, ignore a dúvida do e-mail anterior. Era \"main\" e não \"mail\". Mas já entendi como usar, eu acho");
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

            System.out.println("Feito!!!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}