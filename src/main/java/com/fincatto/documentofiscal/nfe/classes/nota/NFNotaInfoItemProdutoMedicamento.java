package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.nfe.validadores.BigDecimalParser;
import com.fincatto.documentofiscal.nfe.validadores.StringValidador;
import java.math.BigDecimal;
import org.joda.time.LocalDate;
import org.simpleframework.xml.Element;

public class NFNotaInfoItemProdutoMedicamento extends DFBase {

    @Element(name = "cProdANVISA", required = false)//Trocar required true na 4.00
    private String codigoAnvisa;
    
    @Element(name = "nLote", required = false)//3.10 - retirar para 4.00
    private String lote;

    @Element(name = "qLote", required = false)//3.10 - retirar para 4.00
    private String quantidade;

    @Element(name = "dFab", required = false)//3.10 - retirar para 4.00
    private LocalDate dataFabricacao;

    @Element(name = "dVal", required = false)//3.10 - retirar para 4.00
    private LocalDate dataValidade;

    @Element(name = "vPMC", required = true)
    private String precoMaximoConsumidor;

    public NFNotaInfoItemProdutoMedicamento() {
        this.lote = null;
        this.quantidade = null;
        this.dataFabricacao = null;
        this.dataValidade = null;
        this.precoMaximoConsumidor = null;
    }

    public void setCodigoAnvisa(String codigoAnvisa) {
        StringValidador.tamanho13(lote, lote);
        this.codigoAnvisa = codigoAnvisa;
    }

    public void setLote(final String lote) {
        StringValidador.tamanho20(lote, "Lote Medicamento");
        this.lote = lote;
    }

    public void setQuantidade(final BigDecimal quantidade) {
        this.quantidade = BigDecimalParser.tamanho11Com3CasasDecimais(quantidade, "Quantidade Medicamento");
    }

    public void setDataFabricacao(final LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public void setDataValidade(final LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setPrecoMaximoConsumidor(final BigDecimal precoMaximoConsumidor) {
        this.precoMaximoConsumidor = BigDecimalParser.tamanho15Com2CasasDecimais(precoMaximoConsumidor, "Preco Maximo Consumidor Medicamento");
    }

    public String getCodigoAnvisa() {
        return codigoAnvisa;
    }

    public String getLote() {
        return this.lote;
    }

    public String getQuantidade() {
        return this.quantidade;
    }

    public LocalDate getDataFabricacao() {
        return this.dataFabricacao;
    }

    public LocalDate getDataValidade() {
        return this.dataValidade;
    }

    public String getPrecoMaximoConsumidor() {
        return this.precoMaximoConsumidor;
    }

}