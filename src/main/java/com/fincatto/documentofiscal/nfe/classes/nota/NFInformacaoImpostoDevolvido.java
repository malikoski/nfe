package com.fincatto.documentofiscal.nfe.classes.nota;

import java.math.BigDecimal;

import org.simpleframework.xml.Element;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.nfe.validadores.BigDecimalParser;

public class NFInformacaoImpostoDevolvido extends DFBase {
    @Element(name = "vIPIDevol", required = true)
    private String valorIPIDevolvido;

    public String getValorIPIDevolvido() {
        return this.valorIPIDevolvido;
    }

    public void setValorIPIDevolvido(final BigDecimal valorIPIDevolvido) {
        this.valorIPIDevolvido = BigDecimalParser.tamanho15Com2CasasDecimais(valorIPIDevolvido, "Valor IPI Devolvido");
    }

}