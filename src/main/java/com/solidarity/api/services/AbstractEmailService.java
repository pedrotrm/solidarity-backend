package com.solidarity.api.services;

import com.solidarity.api.model.Entidade;
import com.solidarity.api.model.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void enviarEmailConfirmacaoVoluntario(Voluntario voluntario) {
        SimpleMailMessage sm = prepararSimpleMailMessageParaVoluntario(voluntario);
        enviarEmail(sm);
    }

    @Override
    public void enviarEmailConfirmacaoVoluntarioHtml(Voluntario voluntario){
        try {
            MimeMessage mm = preparaMimeMessageParaCadastroVoluntario(voluntario);
            enviarEmailHtml(mm);
        }
        catch (MessagingException e){
            enviarEmailConfirmacaoVoluntario(voluntario);
        }
    }

    @Override
    public void enviarEmailConfirmacaoEntidade(Entidade entidade) {
        SimpleMailMessage sm = prepararSimpleMailMessageParaEntidade(entidade);
        enviarEmail(sm);
    }

    @Override
    public void enviarEmailConfirmacaoEntidadeHtml(Entidade entidade){
        try {
            MimeMessage mm = preparaMimeMessageParaCadastroEntidade(entidade);
            enviarEmailHtml(mm);
        }
        catch (MessagingException e){
            enviarEmailConfirmacaoEntidade(entidade);
        }
    }

    protected  MimeMessage preparaMimeMessageParaCadastroVoluntario(Voluntario voluntario) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(voluntario.getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Cadastro efetuado com sucesso ! ");
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlParaTemplateCadastroVoluntario(voluntario), true);
        return mimeMessage;
    }

    protected  MimeMessage preparaMimeMessageParaCadastroEntidade(Entidade entidade) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(entidade.getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Cadastro efetuado com sucesso ! ");
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlParaTemplateCadastroEntidade(entidade), true);
        return mimeMessage;
    }

    protected SimpleMailMessage prepararSimpleMailMessageParaVoluntario(Voluntario voluntario) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(voluntario.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Cadastro efetuado com sucesso !");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Bem vindo "+voluntario.getNome());
        return sm;
    }

    protected SimpleMailMessage prepararSimpleMailMessageParaEntidade(Entidade entidade) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(entidade.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Cadastro efetuado com sucesso !");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Bem vindo "+entidade.getNome());
        return sm;
    }

    protected String htmlParaTemplateCadastroVoluntario(Voluntario voluntario){
        Context context = new Context();
        return templateEngine.process("email/CadastroVoluntario", context);
    }

    protected String htmlParaTemplateCadastroEntidade(Entidade entidade){
        Context context = new Context();
        return templateEngine.process("email/CadastroEntidade", context);
    }
}
