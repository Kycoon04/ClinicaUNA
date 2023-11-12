/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.util;

import cr.ac.una.clinicaws.model.ExcelDto;
import cr.ac.una.clinicaws.model.ReportDto;
import cr.ac.una.clinicaws.service.GenericSql;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class Email {

    private String sourceMail;
    private String destinationMail;
    private String asunt;
    private String archive;
    private String link;
    private String password;
    private String name;
    private String info;
    private String action;
    private String motive;
    private String button;
    private static final Logger LOG = Logger.getLogger(GenericSql.class.getName());

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
        this.action = "";
        this.motive = "";
        this.button = "";
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

    public void envioDeActivacion(Email acti, String idiom) {
        enviarCorreoActiv(acti, idiom);
    }

    /**
     * Método para enviar correos electrónicos relacionados con informes.
     *
     * @param link Enlace que se incluirá en el cuerpo del correo.
     */
    public void envioCmbClave(String link, String idiom) {
        enviarClave(link, idiom);
    }

    public void enviarCorreoReporte(Email acti) {

        System.out.println("Vacia");
        sourceMail = "clinicauna10@gmail.com";
        name = "ClinicaUNA";
        password = "xvezelgtwkeuhawv";
        info = "La mejor en salud";

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
                    + "    background-color: #000359;\n"
                    + "    max-width: 600px;\n"
                    + "    margin: 0 auto;\n"
                    + "    padding: 20px;\n"
                    + "  }\n"
                    + "  header {\n"
                    + "    background-color: #000359;\n"
                    + "    color: white;\n"
                    + "    text-align: center;\n"
                    + "    padding: 10px 0;\n"
                    + "  }\n"
                    + "  h1 {\n"
                    + "    font-size: 24px;\n"
                    + "    margin-bottom: 20px;\n"
                    + "    background-color: #000359;\n"
                    + "  }\n"
                    + "  h2 {\n"
                    + "    font-size: 16px;\n"
                    + "    margin-bottom: 20px;\n"
                    + "    background-color: #000359;\n"
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
                    + "    background-color: #000359;\n"
                    + "    color: white;\n"
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

    public void enviarCorreoActiv(Email acti, String idiom) {

        System.out.println("Vacia");
        sourceMail = "clinicauna10@gmail.com";
        name = "ClinicaUNA";

        password = "xvezelgtwkeuhawv";

        if (idiom.equals("Spanish")) {
            info = "La mejor en salud";
            motive = "Activacion de Cuentas";
            action = "Bienvenido al sistema de salud , para continuar con su proceso de activacion, da clic en el siguiente boton";
            button = "Continuar";

        } else if (idiom.equals("English")) {
            info = "The best in health";
            motive = "Account Activation";
            action = "Welcome to the health system, to continue with your activation process, click the following button.";
            button = "Continue";

        } else if (idiom.equals("French")) {
            info = "Le meilleur en matière de santé";
            motive = "Activation de Compte";
            action = "Bienvenue dans le système de santé, pour continuer votre processus d'activation, cliquez sur le bouton suivant.";
            button = "continuer";
        } else if (idiom.equals("Japanese")) {
            info = "The best in health";
            motive = "Account Activation";
            action = "Welcome to the health system, to continue with your activation process, click the following button.";
            button = "Continue";
        }

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
                    + "    background-color: #000359;\n"
                    + "    max-width: 600px;\n"
                    + "    margin: 0 auto;\n"
                    + "    padding: 20px;\n"
                    + "  }\n"
                    + "  header {\n"
                    + "    background-color: #000359;\n"
                    + "    color: white;\n"
                    + "    text-align: center;\n"
                    + "    padding: 10px 0;\n"
                    + "  }\n"
                    + "  h1 {\n"
                    + "    font-size: 24px;\n"
                    + "    margin-bottom: 20px;\n"
                    + "    background-color: #000359;\n"
                    + "  }\n"
                    + "  h2 {\n"
                    + "    font-size: 16px;\n"
                    + "    margin-bottom: 20px;\n"
                    + "    background-color: #000359;\n"
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
                    + "    background-color: #000359;\n"
                    + "    color: white;\n"
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
                    + "<h1>" + motive + " " + name + "</h1>\n"
                    + "<h2>" + action + "</h2>\n"
                    + "<h2>" + info + "</h2>\n"
                    + "</header>\n"
                    + "<div class='container'>\n"
                    + "<p class='center'><a id='activeButton' href='" + acti.getLink() + "'>" + button + "</a></p>\n"
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

    public void enviarConfirmacion2(String dia, String nombre, String idiom) {

        sourceMail = "clinicauna10@gmail.com";
        name = "ClinicaUNA";

        password = "xvezelgtwkeuhawv";

        if (idiom.equals("Spanish")) {
            info = "Te Esperamos!";
            motive = "Confirmación de cita";
            action = "Te confirmamos tu cita para el dia";
            button = "Continuar";

        } else if (idiom.equals("English")) {
            info = "We are waiting for you!";
            motive = "Appointment Confirmation";
            action = "We confirm your appointment for the day";
            button = "Continue";

        } else if (idiom.equals("French")) {
            info = "Nous t'attendons!";
            motive = "Confirmation de rendez-vous";
            action = "Nous confirmons ton rendez-vous pour le jour";
            button = "Continuer";
        } else {
            info = "We are waiting for you!";
            motive = "Appointment Confirmation";
            action = "We confirm your appointment for the day";
            button = "Continue";

        }

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
                    + "h1 { color: #white; background-color: 000359;  text-align: center; }"
                    + "p { color: #666; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h1>" + motive + "</h1>"
                    + "<p>Hola " + nombre + "!</p>"
                    + "<p>" + action + " " + dia + "</p>"
                    + "<p>" + info + "</p>"
                    + "<p><strong>" + destinationMail + "</strong></p>"
                    + "</body>"
                    + "</html>";

            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(sourceMail));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinationMail));
            mensaje.setSubject("Confirmación");

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

    public void enviarExcel(ExcelDto excelDto, File archivoAdjunto, List<String> destinationMails) {

        sourceMail = "clinicauna10@gmail.com";
        name = "ClinicaUNA";

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
                    + "h1 { color: #000359; background-color: 000359;  text-align: center; }"
                    + "p { color: #666; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h1>"+excelDto.getParametersDto().getPsName()+"</h1>"
                    + "<p>"+excelDto.getParametersDto().getPsDescription()+"</p>"
                    + "</body>"
                    + "</html>";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mensajeHTML, "text/html");
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(archivoAdjunto);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(archivoAdjunto.getName());
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(attachmentBodyPart);
            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(sourceMail));
            for (String mail : destinationMails) {
                mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            }
            mensaje.setSubject(excelDto.getParametersDto().getPsName());
            mensaje.setContent(multipart);
            // Enviar el correo
            Transport t = s.getTransport("smtp");
            t.connect(sourceMail, password);
            if (t.isConnected()) {
                t.sendMessage(mensaje, mensaje.getAllRecipients());
                t.close();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void enviarRecordatorio(String dia, String nombre, String idiom) {

        sourceMail = "clinicauna10@gmail.com";
        name = "ClinicaUNA";
        info = "La mejor en salud";
        password = "xvezelgtwkeuhawv";

        if (idiom.equals("Spanish")) {
            info = "Te Esperamos!";
            motive = "Recordatorio de cita";
            action = "Te recordamos tu cita para el dia";
            button = "Continuar";

        } else if (idiom.equals("English")) {
            info = "We are waiting for you!";
            motive = "Appointment reminder";
            action = "We remind you of your appointment for the day";
            button = "Continue";

        } else if (idiom.equals("French")) {
            info = "Nous t'attendons!";
            motive = "Rappel de rendez-vous";
            action = "Nous vous rappelons votre rendez-vous du jour";
            button = "Continuer";
        } else {
            info = "We are waiting for you!";
            motive = "Appointment reminder";
            action = "We remind you of your appointment for the day";
            button = "Continue";
        }

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
                    + "<h1>" + motive + "</h1>"
                    + "<p>Hola " + nombre + "!</p>"
                    + "<p>" + action + " " + dia + "</p>"
                    + "<p>" + info + "</p>"
                    + "<p><strong>" + destinationMail + "</strong></p>"
                    + "</body>"
                    + "</html>";

            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(sourceMail));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinationMail));
            mensaje.setSubject("Confirmación");

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

    public void enviarClave(String link, String idiom) {

        sourceMail = "clinicauna10@gmail.com";
        name = "ClinicaUNA";
        info = "La mejor en salud";
        password = "xvezelgtwkeuhawv";

        if (idiom.equals("Spanish")) {
            info = "Te Esperamos!";
            motive = "Recuperación de Contraseña";
            action = "Se ha detectado un intento de recuperación de contraseña para la dirección de correo ";
            button = "Aqui esta su contraseña temporal ";

        } else if (idiom.equals("English")) {
            info = "We are waiting for you!";
            motive = "Password Recovery";
            action = "A password recovery attempt has been detected for the email address";
            button = "Here is your temporary password";

        } else if (idiom.equals("French")) {
            info = "Nous t'attendons!";
            motive = "Récupération de Mot de Passe";
            action = "Une tentative de récupération de mot de passe a été détectée pour l'adresse email";
            button = "Voici votre mot de passe temporaire";
        } else {
            info = "We are waiting for you!";
            motive = "Password Recovery";
            action = "A password recovery attempt has been detected for the email address";
            button = "Here is your temporary password";

        }

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
                    + "body { font-family: Arial, sans-serif; background-color: #000359; }"
                    + "h1 { color: White; background-color: #000359;  text-align: center; }"
                    + "p { color: #666; }"
                    + "#imageLogo{ width: 340px;  height: auto; margin: 10px; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h1>" + motive + "</h1>"
                    + "<p>" + action + " " + "</p>"
                    + "<p><strong>" + destinationMail + "</strong></p>"
                    + "<img id='imageLogo' src='https://eblbeoe.stripocdn.email/content/guids/CABINET_6900d7cefb295c65d827b1830fdf29ebf3b01e24733c06a4ea87c88a9f13230d/images/logomedicalclinic.png' alt='Imagen 1'>"
                    + "<p>" + button + "</p>"
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
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void enviarReporteControl(ReportDto report, String EmailDest) {

        String Presion;
        String FrecuenciaCardi;
        String Altura;
        String Peso;
        String Temperatura;
        String IMC;
        String observaciones;
        String Razon;
        String fechaHora;
        String nombrePaciente;
        String ExamenFisico;
        String Tratamiento;
        String PlanCuidados;

        if (report.getRtAppointment().getAtUserregister().getUsLenguage().equals("Spanish")) {
            Presion = "Presion: ";
            FrecuenciaCardi = "Frecuencia Cardica: ";
            Altura = "Altura: ";
            Peso = "Peso: ";
            Temperatura = "Temperatura: ";
            IMC = "IMC: ";
            observaciones = "Observaciones: ";
            Razon = "Razon: ";
            fechaHora = "Fecha/Hora: ";
            nombrePaciente = "Nombre: ";
            ExamenFisico = "Examen Fisico: ";
            Tratamiento = "Tratamiento: ";
            PlanCuidados = "Cuidados: ";
        } else if (report.getRtAppointment().getAtUserregister().getUsLenguage().equals("English")) {
            Presion = "Blood Pressure: ";
            FrecuenciaCardi = "Heart Rate: ";
            Altura = "Height: ";
            Peso = "Weight: ";
            Temperatura = "Temperature: ";
            IMC = "BMI: ";
            observaciones = "Observations: ";
            Razon = "Reason: ";
            fechaHora = "Date/Time: ";
            nombrePaciente = "Patient's Name: ";
            ExamenFisico = "Physical Examination: ";
            Tratamiento = "Treatment: ";
            PlanCuidados = "Care Plan: ";

        } else if (report.getRtAppointment().getAtUserregister().getUsLenguage().equals("French")) {
            Presion = "Pression artérielle : ";
            FrecuenciaCardi = "Fréquence cardiaque : ";
            Altura = "Taille : ";
            Peso = "Poids : ";
            Temperatura = "Température : ";
            IMC = "IMC (Indice de Masse Corporelle) : ";
            observaciones = "Observations : ";
            Razon = "Raison : ";
            fechaHora = "Date/Heure : ";
            nombrePaciente = "Nom du patient : ";
            ExamenFisico = "Examen physique : ";
            Tratamiento = "Traitement : ";
            PlanCuidados = "Plan de soins : ";

        } else {
            Presion = "Blood Pressure: ";
            FrecuenciaCardi = "Heart Rate: ";
            Altura = "Height: ";
            Peso = "Weight: ";
            Temperatura = "Temperature: ";
            IMC = "BMI: ";
            observaciones = "Observations: ";
            Razon = "Reason: ";
            fechaHora = "Date/Time: ";
            nombrePaciente = "Patient's Name: ";
            ExamenFisico = "Physical Examination: ";
            Tratamiento = "Treatment: ";
            PlanCuidados = "Care Plan: ";

        }

        sourceMail = "clinicauna10@gmail.com";
        name = "ClinicaUNA";
        info = "La mejor en salud";
        password = "xvezelgtwkeuhawv";
        destinationMail = EmailDest;

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
                    + "h1 { color: WHITE; background-color: #000359;  text-align: center; }"
                    + "p { color: #666; }"
                    + ".flex-container { display: flex; justify-content: space-between; }" // Establecer flexbox
                    + ".left-section { flex: 1; }" // Ajustar el tamaño de la sección izquierdae
                    + ".right-section { flex: 1; text-align: right; }" // Ajustar el tamaño y alinear a la derecha
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h1>Reporte de Control</h1>"
                    + "<div class='flex-container'>"
                    + "<div class='left-section'>"
                    + "<p>" + fechaHora + report.getRtDate() + "</p>"
                    + "<p>" + nombrePaciente + report.getRtAppointment().getAtPatient().getPtName() + "</p>"
                    + "<p>" + Presion + "" + report.getRtPressure() + "</p>"
                    + "<p>" + FrecuenciaCardi + report.getRtHeartRate() + "</p>"
                    + "<p>" + Altura + report.getRtHeight() + "</p>"
                    + "<p>" + Peso + report.getRtWeight() + "</p>"
                    + "<p>" + Temperatura + report.getRtTemperature() + "</p>"
                    + "</div>"
                    + "<div class='right-section'>"
                    + "<p>" + IMC + report.getRtBodyMass() + "</p>"
                    + "<p>" + observaciones + report.getRtObservations() + "</p>"
                    + "<p>" + Razon + report.getRtNotesNursing() + "</p>"
                    + "<p>" + ExamenFisico + report.getRtFisicExamen() + "</p>"
                    + "<p>" + Tratamiento + report.getRtTreatmentExamen() + "</p>"
                    + "<p>" + PlanCuidados + report.getRtCarePlan() + "</p>"
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";
            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(sourceMail));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinationMail));
            mensaje.setSubject("Reporte de Control");

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
