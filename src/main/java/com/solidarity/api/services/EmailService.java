package com.solidarity.api.services;

import com.solidarity.api.model.Entidade;
import com.solidarity.api.model.Voluntario;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void enviarEmailConfirmacaoVoluntario(Voluntario voluntario);

    void enviarEmailConfirmacaoVoluntarioHtml(Voluntario voluntario);

    void enviarEmailConfirmacaoEntidade(Entidade entidade);

    void enviarEmailConfirmacaoEntidadeHtml(Entidade entidade);

    void enviarEmail(SimpleMailMessage msg);

    void enviarEmailHtml(MimeMessage msg);


}
