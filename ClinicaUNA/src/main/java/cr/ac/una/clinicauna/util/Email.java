package utils;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @author BiblioPZ UNA
 */
public class Email {

    private String correoDeOrigen;
    private String correoDeDestino;
    private String asunto;
    private String lenguaje;
    private String usuario;

    public Email(String destino, String lenguaje, String usuario, String asunto) {
        this.correoDeOrigen = "clinicauna10@gmail.com";
        this.correoDeDestino = destino;
        this.lenguaje = lenguaje;
        this.usuario = usuario;
        this.asunto = asunto;
    }

    public void envioDeCorreos(String link) {
        enviarCorreo(link);
    }

    public void envioCmbClave(String correo, String link) {
        enviarClave(correo, link);
    }

    public void envioDeUser(String user) {
        enviarUser(user);
    }

    public void enviarCorreoReporte(String archivo) {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", correoDeOrigen);
            p.setProperty("mail.smtp.auth", "true");
            Session s = Session.getDefaultInstance(p);

            BodyPart texto = new MimeBodyPart();
            if (lenguaje.equals("espanhol")) {
                texto.setText("Adjuntamos su reporte, gracias por utilizar CineUNA");
            } else {
                texto.setText("We attach your report, thanks for using CineUNA app");
            }
            texto.setDataHandler(new DataHandler(new FileDataSource(archivo + ".pdf")));
            texto.setFileName("reporte.pdf");
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);

            MimeMessage mensaje = new MimeMessage(s);

            mensaje.setFrom(new InternetAddress(correoDeOrigen));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDeDestino));
            mensaje.setSubject(asunto);
            mensaje.setContent(m);

            Transport t = s.getTransport("smtp");
            t.connect(correoDeOrigen, "xvezelgtwkeuhawv");
            if (t.isConnected()) {
                t.sendMessage(mensaje, mensaje.getAllRecipients());
                t.close();
            }
        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }

    private void enviarCorreo(String enlace) {
        try {
    
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.user", correoDeOrigen);
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            String mensajeHTML = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<style>\n"
                    + "  body {\n"
                    + "    font-family: Arial, sans-serif;\n"
                    + "    background-color: #212121;\n"
                    + "    max-width: 600px;\n"
                    + "    margin: 0 auto;\n"
                    + "    padding: 20px;\n"
                    + "  }\n"
                    + "  header {\n"
                    + "    background-color: #333;\n"
                    + "    color: white;\n"
                    + "    text-align: center;\n"
                    + "    padding: 10px 0;\n"
                    + "  }\n"
                    + "  h1 {\n"
                    + "    font-size: 24px;\n"
                    + "    margin-bottom: 20px;\n"
                    + "    background-color: #333;\n"
                    + "  }\n"
                    + "  h2 {\n"
                    + "    font-size: 16px;\n"
                    + "    margin-bottom: 20px;\n"
                    + "    background-color: #333;\n"
                    + "    padding: 10px;\n"
                    + "  }\n"
                    + "  p {\n"
                    + "    font-size: 18px;\n"
                    + "    line-height: 1.6;\n"
                    + "  }\n"
                    + "  .center {\n"
                    + "    text-align: center;\n"
                    + "  }\n"
                    + "  #activeButton {\n"
                    + "    font-family: 'Tw Cen MT';\n"
                    + "    font-size: 18px;\n"
                    + "    display: inline-block;\n"
                    + "    padding: 10px 20px;\n"
                    + "    background-color: #76ff03;\n"
                    + "    color: black;\n"
                    + "    text-decoration: none;\n"
                    + "    border-radius: 5px;\n"
                    + "    transition: background-color 0.3s ease;\n"
                    + "    cursor: pointer;\n"
                    + "  }\n"
                    + "  #activeButton:hover {\n"
                    + "    background-color: #5ac500;\n"
                    + "  }\n"
                    + "  a {\n"
                    + "    color: #007bff;\n"
                    + "    text-decoration: none;\n"
                    + "  }\n"
                    + "  ul {\n"
                    + "    list-style: none;\n"
                    + "    padding: 0;\n"
                    + "  }\n"
                    + "  li {\n"
                    + "    margin-bottom: 10px;\n"
                    + "  }\n"
                    + "</style>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<header>\n"
                    + "<h1>" + asunto + "</h1>\n"
                    + "<h2>Bienvenido al sistema de evaluaciones 360, para continuar con su proceso de activacion, da clic en el siguiente boton</h2>\n"
                    + "<h2>" + asunto + "</h2>\n"
                    + "</header>\n"
                    + "<div class='container'>\n"
                    + "<p class='center'><a id='activeButton' href='" + enlace + "'>Continuar</a></p>\n"
                    + "</div>\n"
                    + "</body>\n"
                    + "</html>";

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(mensajeHTML, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);

            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(correoDeOrigen));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDeDestino));
            mensaje.setSubject(asunto);
            mensaje.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(correoDeOrigen, "xvezelgtwkeuhawv");
            if (transport.isConnected()) {
                transport.sendMessage(mensaje, mensaje.getAllRecipients());
                transport.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarClave(String correo, String link) {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", correoDeOrigen);
            p.setProperty("mail.smtp.auth", "true");
            Session s = Session.getDefaultInstance(p);

            BodyPart texto = new MimeBodyPart();
            if (lenguaje.equals("espanhol")) {
                texto.setText("nueva contraseña temporal : "
                        + link);
            } else {
                texto.setText(" new temporal password : "
                        + link);
            }

            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);

            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(correoDeOrigen));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDeDestino));
            mensaje.setSubject(asunto);
            mensaje.setContent(m);

            Transport t = s.getTransport("smtp");
            t.connect(correoDeOrigen, "xvezelgtwkeuhawv");
            if (t.isConnected()) {
                t.sendMessage(mensaje, mensaje.getAllRecipients());
                t.close();
            }
            System.out.printf("Mensaje enviado");
        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }

    private void enviarUser(String user) {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", correoDeOrigen);
            p.setProperty("mail.smtp.auth", "true");
            Session s = Session.getDefaultInstance(p);

            BodyPart texto = new MimeBodyPart();
            if (lenguaje.equals("espanhol")) {
                texto.setText("Su nombre de usuario es: "
                        + user);
            } else {
                texto.setText("Your username is: "
                        + user);
            }

            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);

            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(correoDeOrigen));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDeDestino));
            mensaje.setSubject(asunto);
            mensaje.setContent(m);

            Transport t = s.getTransport("smtp");
            t.connect(correoDeOrigen, "xvezelgtwkeuhawv");
            if (t.isConnected()) {
                t.sendMessage(mensaje, mensaje.getAllRecipients());
                t.close();
            }
            System.out.printf("Mensaje enviado");
        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }

}
