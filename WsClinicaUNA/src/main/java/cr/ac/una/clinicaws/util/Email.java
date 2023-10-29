/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.util;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

public class Email {
 private String sourceMail;
    private String destinationMail;
    private String asunt;
    private String archive;
    private String link;
    private String password;
    private String name;
    private String info;

    /**
     * Constructor de la clase Email.
     *
     * @param destino Dirección de correo electrónico del destinatario.
     * @param usuario Dirección de correo electrónico del remitente.
     * @param asunto Asunto del correo electrónico.
     */
    public Email(String destino, String asunto, String linkGen) {
        this.sourceMail = "clinicauna10@gmail.com";
        this.destinationMail = destino;
        this.asunt = asunto;
        this.link = linkGen;
        this.password = "";
        this.name = "";
        this.info = "";
        this.archive = "";
    }

    public Email() {
    }

    /**
     * Método para enviar correos electrónicos relacionados con informes.
     *
     * @param link Enlace que se incluirá en el cuerpo del correo.
     */
    public void envioDeCorreos(Email acti) {
        enviarCorreoReporte(acti);
    }
    /**
     * Método para enviar correos electrónicos relacionados con informes.
     *
     * @param link Enlace que se incluirá en el cuerpo del correo.
     */
    public void envioCmbClave(String link) {
        enviarClave(link);
    }

    public void enviarCorreoReporte(Email acti) {
   
            System.out.println("Vacia");
            sourceMail = "clinicauna10@gmail.com";
            name = "ClinicaUNA";
            info = "La mejor en salud";
            password = "xvezelgtwkeuhawv";
  
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.user", sourceMail);
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
                    + "<h1>Activacion de Cuentas " + name + "</h1>\n"
                    + "<h2>Bienvenido al sistema de salud , para continuar con su proceso de activacion, da clic en el siguiente boton</h2>\n"
                    + "<h2>" + info + "</h2>\n"
                    + "</header>\n"
                    + "<div class='container'>\n"
                    + "<p class='center'><a id='activeButton' href='" + acti.getLink() + "'>Continuar</a></p>\n"
                    + "</div>\n"
                    + "</body>\n"
                    + "</html>";

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(mensajeHTML, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);

            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(sourceMail));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinationMail));
            mensaje.setSubject(asunt);
            mensaje.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(sourceMail, password);
            if (transport.isConnected()) {
                transport.sendMessage(mensaje, mensaje.getAllRecipients());
                transport.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarClave(String link) {

      
            sourceMail = "clinicauna10@gmail.com";
            name = "ClinicaUNA";
            info = "La mejor en salud";
            password = "xvezelgtwkeuhawv";
        

        try {
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", sourceMail);
            p.setProperty("mail.smtp.auth", "true");
            Session s = Session.getDefaultInstance(p);

            String mensajeHTML = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; background-color: #f2f2f2; }"
                    + "h1 { color: #333; background-color: grey;  text-align: center; }"
                    + "p { color: #666; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h1>Recuperación de Contraseña</h1>"
                    + "<p>Se ha detectado un intento de recuperación de contraseña para la dirección de correo:</p>"
                    + "<p><strong>" + destinationMail + "</strong></p>"
                    + "<p>Aquí está su contraseña temporal:</p>"
                    + "<p><strong>" + link + "</strong></p>"
                    + "</body>"
                    + "</html>";

            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(sourceMail));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinationMail));
            mensaje.setSubject(asunt);

            mensaje.setContent(mensajeHTML, "text/html");

            Transport t = s.getTransport("smtp");
            t.connect(sourceMail, password);
            if (t.isConnected()) {
                t.sendMessage(mensaje, mensaje.getAllRecipients());
                t.close();
            }
            System.out.printf("Mensaje enviado");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public String getSourceMail() {
        return sourceMail;
    }

    public void setSourceMail(String sourceMail) {
        this.sourceMail = sourceMail;
    }

    public String getDestinationMail() {
        return destinationMail;
    }

    public void setDestinationMail(String destinationMail) {
        this.destinationMail = destinationMail;
    }

    public String getAsunt() {
        return asunt;
    }

    public void setAsunt(String asunt) {
        this.asunt = asunt;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    
    
    
}
