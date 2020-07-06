package com.solidarity.api.model.enums;

public enum Habilidade {

    ARTES_TRABALHO_MANUAL(1, "Artes/ Trabalho Manula"),
    COMUNICACAO(2, "Comunicação"),
    DANCA_MUSICA(3, "Dança/Música"),
    DIREITO(4, "Direito"),
    EDUCACAO(5, "Educação"),
    ESPORTES(6, "Esportes"),
    COZINHA(7, "Cozinha"),
    GERENCIAMENTO(8, "Gerenciamento"),
    IDIOMAS(9, "Idiomas"),
    COMPUTADORES_TECNOLOGIA(10, "Computadores/Tecnologia"),
    SAUDE(11, "Saúde"),
    AGILIDADE(12, "Agilidade"),
    OUTROS(13, "Outros");


    private int code;
    private String descricao;


    private Habilidade(int code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    public int getCode(){
        return code;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Habilidade valorDe(int code){
        for(Habilidade valor : Habilidade.values()){
            if (valor.getCode() == code)
                return valor;
        }
        throw new IllegalArgumentException("Causa Invalida !");

    }




}
