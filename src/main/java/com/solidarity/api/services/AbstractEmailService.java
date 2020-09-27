package com.solidarity.api.services;

import com.solidarity.api.model.Voluntario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void enviarEmailConfirmacaoVoluntario(Voluntario voluntario) {
        SimpleMailMessage sm = prepararSimpleMailMessageParaVoluntario(voluntario);
        enviarEmail(sm);
    }

    private SimpleMailMessage prepararSimpleMailMessageParaVoluntario(Voluntario voluntario) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(voluntario.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Cadastro efetuado com sucesso !");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Bem vindo "+voluntario.getNome());
        return sm;
    }
}
