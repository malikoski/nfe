package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.nfe.classes.NFNotaSituacaoOperacionalSimplesNacional;
import com.fincatto.documentofiscal.nfe.classes.NFOrigem;
import com.fincatto.documentofiscal.nfe.validadores.BigDecimalParser;
import java.math.BigDecimal;
import org.simpleframework.xml.Element;

public class NFNotaInfoItemImpostoICMSSN500 extends DFBase {

    @Element(name = "orig", required = true)
    private NFOrigem origem;

    @Element(name = "CSOSN", required = true)
    private NFNotaSituacaoOperacionalSimplesNacional situacaoOperacaoSN;

    @Element(name = "vBCSTRet", required = false)
    private String valorBCICMSSTRetido;

    @Element(name = "vICMSSTRet", required = false)
    private String valorICMSSTRetido;

    @Element(name = "vBCFCPSTRet", required = false)
    private String baseCalculoFcpStRetido;

    @Element(name = "pFCPSTRet", required = false)
    private String percentualFcpStRetido;

    @Element(name = "vFCPSTRet", required = false)
    private String valorFcpStRetido;

    @Element(name = "pST", required = false)
    private String aliquotaConsumidorFinal;

    public void setOrigem(final NFOrigem origem) {
        this.origem = origem;
    }

    public void setSituacaoOperacaoSN(final NFNotaSituacaoOperacionalSimplesNacional situacaoOperacaoSN) {
        this.situacaoOperacaoSN = situacaoOperacaoSN;
    }

    public void setValorBCICMSSTRetido(final BigDecimal valorBCICMSSTRetido) {
        this.valorBCICMSSTRetido = BigDecimalParser.tamanho15Com2CasasDecimais(valorBCICMSSTRetido,
                "Valor BC ICMS ST Retido ICMSSN500");
    }

    public void setValorICMSSTRetido(final BigDecimal valorICMSSTRetido) {
        this.valorICMSSTRetido = BigDecimalParser.tamanho15Com2CasasDecimais(valorICMSSTRetido,
                "Valor ICMS ST Retido ICMSSN500");
    }

    public void setBaseCalculoFcpStRetido(BigDecimal baseCalculoFcpStRetido) {
        this.baseCalculoFcpStRetido = BigDecimalParser.tamanho15Com2CasasDecimais(baseCalculoFcpStRetido,
                "Valor da Base de Cálculo do FCP");
    }

    public void setPercentualFcpStRetido(BigDecimal percentualFcpStRetido) {
        this.percentualFcpStRetido = BigDecimalParser.tamanho7ComAte4CasasDecimais(percentualFcpStRetido,
                "Percentual do FCP retido por Substituição Tributária");
    }

    public void setValorFcpStRetido(BigDecimal valorFcpStRetido) {
        this.valorFcpStRetido = BigDecimalParser.tamanho15Com2CasasDecimais(valorFcpStRetido,
                "Valor do FCP retido por Substituição Tributária");
    }

    public void setAliquotaConsumidorFinal(BigDecimal aliquotaConsumidorFinal) {
        this.aliquotaConsumidorFinal = BigDecimalParser.tamanho7ComAte4CasasDecimais(aliquotaConsumidorFinal,
                "Alíquota suportada pelo Consumidor Final");
    }

    public NFOrigem getOrigem() {
        return this.origem;
    }

    public NFNotaSituacaoOperacionalSimplesNacional getSituacaoOperacaoSN() {
        return this.situacaoOperacaoSN;
    }

    public String getValorBCICMSSTRetido() {
        return this.valorBCICMSSTRetido;
    }

    public String getValorICMSSTRetido() {
        return this.valorICMSSTRetido;
    }

    public String getBaseCalculoFcpStRetido() {
        return baseCalculoFcpStRetido;
    }

    public String getPercentualFcpStRetido() {
        return percentualFcpStRetido;
    }

    public String getValorFcpStRetido() {
        return valorFcpStRetido;
    }

    public String getAliquotaConsumidorFinal() {
        return aliquotaConsumidorFinal;
    }

}
