package com.solidarity.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;


public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void enviarEmail(SimpleMailMessage msg) {
        LOG.info("Simulando envio de email");
        LOG.info(msg.toString());
        LOG.info("Email enviado");
    }

    @Override
    public void enviarEmailHtml(MimeMessage msg) {
        LOG.info("Simulando envio de email HTML ...");
        LOG.info(msg.toString());
        LOG.info("Email enviado");
    }

}
