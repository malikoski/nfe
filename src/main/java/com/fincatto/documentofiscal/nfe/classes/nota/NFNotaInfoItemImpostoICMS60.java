package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.nfe.classes.NFNotaInfoImpostoTributacaoICMS;
import com.fincatto.documentofiscal.nfe.classes.NFOrigem;
import com.fincatto.documentofiscal.nfe.validadores.BigDecimalParser;
import java.math.BigDecimal;
import org.simpleframework.xml.Element;

public class NFNotaInfoItemImpostoICMS60 extends DFBase {

    @Element(name = "orig", required = true)
    private NFOrigem origem;

    @Element(name = "CST", required = true)
    private NFNotaInfoImpostoTributacaoICMS situacaoTributaria;

    @Element(name = "vBCSTRet", required = false)
    private String valorBCICMSSTRetido;

    @Element(name = "vICMSSTRet", required = false)
    private String valorICMSSTRetido;

    @Element(name = "vBCFCPST", required = false)
    private String baseCalculoFcpSt;

    @Element(name = "pFCPSTRet", required = false)
    private String percentualFcpStRetido;

    @Element(name = "vFCPSTRet", required = false)
    private String valorFcpStRetido;

    @Element(name = "pST", required = false)
    private String aliquotaConsumidorFinal;

    public void setOrigem(final NFOrigem origem) {
        this.origem = origem;
    }

    public void setSituacaoTributaria(final NFNotaInfoImpostoTributacaoICMS situacaoTributaria) {
        this.situacaoTributaria = situacaoTributaria;
    }

    public void setValorBCICMSSTRetido(final BigDecimal valorBCICMSSTRetido) {
        this.valorBCICMSSTRetido = BigDecimalParser.tamanho15Com2CasasDecimais(valorBCICMSSTRetido,
                "Valor BC ICMS ST Retido ICMS60 Item");
    }

    public void setValorICMSSTRetido(final BigDecimal valorICMSSTRetido) {
        this.valorICMSSTRetido = BigDecimalParser.tamanho15Com2CasasDecimais(valorICMSSTRetido,
                "Valor ICMS ST Retido ICMS60 Item");
    }

    public void setBaseCalculoFcpSt(BigDecimal baseCalculoFcpSt) {
        this.baseCalculoFcpSt = BigDecimalParser.tamanho15Com2CasasDecimais(baseCalculoFcpSt,
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

    public NFNotaInfoImpostoTributacaoICMS getSituacaoTributaria() {
        return this.situacaoTributaria;
    }

    public String getValorBCICMSSTRetido() {
        return this.valorBCICMSSTRetido;
    }

    public String getValorICMSSTRetido() {
        return this.valorICMSSTRetido;
    }

    public String getBaseCalculoFcpSt() {
        return baseCalculoFcpSt;
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
