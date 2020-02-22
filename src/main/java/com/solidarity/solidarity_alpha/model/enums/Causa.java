package com.solidarity.solidarity_alpha.model.enums;

public enum Causa {

    TERINAMENTO_PROFISSIONAL(0),
    COMBATE_POBREZA(1),
    CONSUMO_CONSIENTE(2),
    CULTURA_E_ARTE(3),
    DIREITOS_HUMANOS(4),
    EDUCACAO(5),
    CRIANCAS(6),
    IDOSOS(7),
    MEIO_AMBIENTE(8),
    CIDADANIA(9),
    PROTECAO_ANIMAL(10),
    SAUDE(11),
    POLITICA(12),
    JOVENS(13),
    MULHERES(14),
    ESPORTES(15),
    REGUGIADOS(16),
    LGBTQ(17),
    SUSTENTABILIDADE(18),
    OUTROS(19);

    private int code;

    private Causa (int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static Causa valorDe(int code){
        for(Causa valor : Causa.values()){
            if (valor.getCode() == code)
                return valor;
        }
        throw new IllegalArgumentException("Causa Invalida !");

    }
}




