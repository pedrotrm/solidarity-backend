package com.solidarity.api.services;

import com.solidarity.api.model.Voluntario;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void enviarEmailConfirmacaoVoluntario(Voluntario voluntario);

    void enviarEmail(SimpleMailMessage msg);

}
