package com.solidarity.solidaritybackend.model.enums;

public enum Causa {

    TERINAMENTO_PROFISSIONAL(1, "Treinamento Profissional"),
    COMBATE_POBREZA(2, "Combate à Pobreza"),
    CONSUMO_CONSIENTE(3, "Consumo consciente"),
    CULTURA_E_ARTE(4, "Cultura e Arte"),
    DIREITOS_HUMANOS(5, "Direitos Humanos"),
    EDUCACAO(6, "Educação"),
    CRIANCAS(7, "Crianças"),
    IDOSOS(8, "Idosos"),
    MEIO_AMBIENTE(9, "Meio Ambiente"),
    CIDADANIA(10, "Cidadania"),
    PROTECAO_ANIMAL(11, "Proteção Animal"),
    SAUDE(12, "Saúde"),
    POLITICA(13, "Política"),
    JOVENS(14, "Jovens"),
    MULHERES(15, "Mulheres"),
    ESPORTES(16, "Esportes"),
    REGUGIADOS(17, "Refugiados"),
    LGBTQ(18, "LGBTQ+"),
    SUSTENTABILIDADE(19, "Sustentabilidade"),
    COMBATE_AS_DROGAS(20, "Combate as Drogas"),
    OUTROS(21, "Outros");

    private int code;
    private String descricao;

   private Causa(int code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    public int getCode(){
        return code;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Causa valorDe(int code){
        for(Causa valor : Causa.values()){
            if (valor.getCode() == code)
                return valor;
        }
        throw new IllegalArgumentException("Causa Invalida !");

    }
}




