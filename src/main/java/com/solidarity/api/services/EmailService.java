package com.solidarity.api.services;

import com.solidarity.api.model.Voluntario;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void enviarEmailConfirmacaoVoluntario(Voluntario voluntario);

    void enviarEmail(SimpleMailMessage msg);

    void enviarEmailConfirmacaoVoluntarioHtml(Voluntario voluntario);

    void enviarEmailHtml(MimeMessage msg);

}
