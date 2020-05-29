package com.solidarity.solidaritybackend.model.enums;

public enum TipoVaga {

    RECORRENTE(1, "Recorrente"),
    PONTUAIS(2, "Pontuais");

    private int code;
    private String descricao;

    private TipoVaga(int code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    public int getCode(){
        return code;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoVaga valorDe(int code){
        for(TipoVaga valor : TipoVaga.values()){
            if (valor.getCode() == code)
                return valor;
        }
        throw new IllegalArgumentException("Causa Invalida !");

    }

}
