package com.fincatto.documentofiscal.nfe.classes.nota;

import java.math.BigDecimal;

import org.simpleframework.xml.Element;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.nfe.validadores.BigDecimalParser;

public class NFNotaInfoItemProdutoCombustivelCIDE extends DFBase {

    @Element(name = "qBCProd", required = true)
    private String quantidadeBCCIDE;

    @Element(name = "vAliqProd", required = true)
    private String valorAliquota;

    @Element(name = "vCIDE", required = true)
    private String valor;

    public NFNotaInfoItemProdutoCombustivelCIDE() {
        this.quantidadeBCCIDE = null;
        this.valorAliquota = null;
        this.valor = null;
    }

    public void setQuantidadeBCCIDE(final BigDecimal quantidade) {
        this.quantidadeBCCIDE = BigDecimalParser.tamanho16Com4CasasDecimais(quantidade, "Quantidade Combustivel CIDE");
    }

    public void setValorAliquota(final BigDecimal valorAliquota) {
        this.valorAliquota = BigDecimalParser.tamanho15Com4CasasDecimais(valorAliquota, "Valor Aliquota Combustivel CIDE");
    }

    public void setValor(final BigDecimal valor) {
        this.valor = BigDecimalParser.tamanho15Com2CasasDecimais(valor, "Valor Combustivel CIDE");
    }

    public String getQuantidadeBCCIDE() {
        return this.quantidadeBCCIDE;
    }

    public String getValorAliquota() {
        return this.valorAliquota;
    }

    public String getValor() {
        return this.valor;
    }
}