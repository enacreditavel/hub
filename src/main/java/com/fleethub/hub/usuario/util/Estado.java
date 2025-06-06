package com.fleethub.hub.usuario.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Estado {

	ACRE("AC"), 
	ALAGOAS("AL"), 
	AMAPA("AP"), 
	AMAZONAS("AM"), 
	BAHIA("BA"), 
	CEARA("CE"), 
	DISTRITO_FEDERAL("DF"),
	ESPIRITO_SANTO("ES"), 
	GOIAS("GO"), 
	MARANHAO("MA"), 
	MATO_GROSSO("MT"), 
	MATO_GROSSO_DO_SUL("MS"), 
	MINAS_GERAIS("MG"),
	PARA("PA"), 
	PARAIBA("PB"), 
	PARANA("PR"), 
	PERNAMBUCO("PE"), 
	PIAUI("PI"), 
	RIO_DE_JANEIRO("RJ"),
	RIO_GRANDE_DO_NORTE("RN"), 
	RIO_GRANDE_DO_SUL("RS"), 
	RONDONIA("RO"), 
	RORAIMA("RR"), 
	SANTA_CATARINA("SC"),
	SAO_PAULO("SP"), 
	SERGIPE("SE"), 
	TOCANTINS("TO");

	private final String sigla;

    Estado(String sigla) {
        this.sigla = sigla;
    }

    @JsonValue
    public String getSigla() {
        return sigla;
    }

    @JsonCreator
    public static Estado fromSigla(String sigla) {
        for (Estado estado : values()) {
            if (estado.sigla.equalsIgnoreCase(sigla)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Invalid sigla: " + sigla);
    }
}
