package com.solidarity.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("solidarity")
public class SolidarityProperty {

    private final Seguranca seguranca = new Seguranca();

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public static class Seguranca {

        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }

    }

}

