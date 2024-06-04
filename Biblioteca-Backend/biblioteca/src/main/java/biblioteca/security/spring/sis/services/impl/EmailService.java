package biblioteca.security.spring.sis.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Async
    public void sendVerificationCode(String email, String code) throws MessagingException {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setFrom("bad1152024@gmail.com");
            helper.setSubject("Código de verificación");

            Context context = new Context();
            context.setVariable("code", code);

            // Procesa la plantilla HTML
            String htmlContent = templateEngine.process("email", context);
            helper.setText(htmlContent, true);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Manejo de errores de envío de correo
            System.err.println("Error al enviar el correo: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de otros errores
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}
