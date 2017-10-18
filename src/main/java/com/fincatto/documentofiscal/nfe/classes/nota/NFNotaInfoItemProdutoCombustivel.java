package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.nfe.validadores.BigDecimalParser;
import com.fincatto.documentofiscal.nfe.validadores.StringValidador;
import java.math.BigDecimal;
import org.simpleframework.xml.Element;

public class NFNotaInfoItemProdutoCombustivel extends DFBase {

    @Element(name = "cProdANP", required = true)
    private String codigoProdutoANP;

    @Element(name = "pMixGN", required = false)
    private String percentualGasNatural;//Retirar na versão 4.00

    @Element(name = "descANP", required = false)
    private String descricaoProdutoAnp;//Deixar obrigatório na versão 4.00

    @Element(name = "pGLP", required = false)
    private String percentualGlp;

    @Element(name = "pGNn", required = false)
    private String percentualGlpNacional;

    @Element(name = "pGNi", required = false)
    private String percentualGasNaturalImportado;

    @Element(name = "vPart", required = false)
    private String valordePartida;

    @Element(name = "CODIF", required = false)
    private String codigoAutorizacaoCOFIF;

    @Element(name = "qTemp", required = false)
    private String quantidade;

    @Element(name = "UFCons", required = true)
    private String uf;

    @Element(name = "CIDE", required = false)
    private NFNotaInfoItemProdutoCombustivelCIDE cide;

    public NFNotaInfoItemProdutoCombustivel() {
        this.codigoProdutoANP = null;
        this.codigoAutorizacaoCOFIF = null;
        this.quantidade = null;
        this.uf = null;
        this.cide = null;
    }

    public void setCodigoProdutoANP(final String codigoProdutoANP) {
        StringValidador.exatamente9(codigoProdutoANP, "Codigo Produto ANP Combustivel");
        this.codigoProdutoANP = codigoProdutoANP;
    }

    public void setCodigoAutorizacaoCODIF(final String codigoAutorizacaoCODIF) {
        StringValidador.tamanho21(codigoAutorizacaoCODIF, "Codigo Autorizacao CODIF Combustivel");
        this.codigoAutorizacaoCOFIF = codigoAutorizacaoCODIF;
    }

    public void setQuantidade(final BigDecimal quantidade) {
        this.quantidade = BigDecimalParser.tamanho16Com4CasasDecimais(quantidade, "Quantidade Combustivel");
    }

    public void setUf(final DFUnidadeFederativa uf) {
        this.uf = uf.getCodigo();
    }

    public void setCide(final NFNotaInfoItemProdutoCombustivelCIDE cide) {
        this.cide = cide;
    }

    public void setPercentualGasNatural(final BigDecimal percentualGasNatural) {
        this.percentualGasNatural = BigDecimalParser.tamanho5Com2CasasDecimais(percentualGasNatural,
                "Percentual Gas Natural Combustivel");
    }

    public void setDescricaoProdutoAnp(final String descricaoProdutoAnp) {
        StringValidador.tamanho2a95(descricaoProdutoAnp, "Descrição do produto conforme ANP");
        this.descricaoProdutoAnp = descricaoProdutoAnp;
    }

    public void setValordePartida(BigDecimal valordePartida) {
        this.valordePartida = BigDecimalParser.tamanho15Com2CasasDecimais(valordePartida, "Valor de partida (cProdANP=210203001)");
    }

    public void setPercentualGlp(final BigDecimal percentualGlp) {
        this.percentualGlp = BigDecimalParser.tamanho5Com2CasasDecimais(percentualGlp,
                "Percentual do GLP derivado do petróleo no produto GLP (cProdANP=210203001)");
    }

    public void setPercentualGasNaturalGlp(final BigDecimal percentualGlpNacional) {
        this.percentualGlpNacional = BigDecimalParser.tamanho5Com2CasasDecimais(percentualGlpNacional, "Percentual de Gás Natural Nacional GLGNn para o produto GLP (cProdANP=210203001)");
    }

    public void setPercentualGasNaturalImportado(final BigDecimal percentualGasNaturalImportado) {
        this.percentualGasNaturalImportado = BigDecimalParser.tamanho5Com2CasasDecimais(percentualGasNaturalImportado, "Percentual de Gás Natural Importado – GLGNi para o produto GLP (cProdANP=210203001)");
    }

    public String getCodigoProdutoANP() {
        return this.codigoProdutoANP;
    }

    public String getPercentualGasNatural() {
        return this.percentualGasNatural;
    }

    public String getCodigoAutorizacaoCOFIF() {
        return this.codigoAutorizacaoCOFIF;
    }

    public String getQuantidade() {
        return this.quantidade;
    }

    public String getUf() {
        return this.uf;
    }

    public String getDescricaoProdutoAnp() {
        return descricaoProdutoAnp;
    }

    public String getPercentualGlp() {
        return percentualGlp;
    }

    public String getPercentualGlpNacional() {
        return percentualGlpNacional;
    }

    public String getPercentualGasNaturalImportado() {
        return percentualGasNaturalImportado;
    }

    public String getValordePartida() {
        return valordePartida;
    }

    public NFNotaInfoItemProdutoCombustivelCIDE getCide() {
        return this.cide;
    }
}
