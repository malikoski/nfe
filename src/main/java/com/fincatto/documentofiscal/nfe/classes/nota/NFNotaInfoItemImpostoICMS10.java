package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.nfe.classes.NFNotaInfoImpostoTributacaoICMS;
import com.fincatto.documentofiscal.nfe.classes.NFNotaInfoItemModalidadeBCICMS;
import com.fincatto.documentofiscal.nfe.classes.NFNotaInfoItemModalidadeBCICMSST;
import com.fincatto.documentofiscal.nfe.classes.NFOrigem;
import com.fincatto.documentofiscal.nfe.validadores.BigDecimalParser;
import java.math.BigDecimal;
import org.simpleframework.xml.Element;

public class NFNotaInfoItemImpostoICMS10 extends DFBase {

    @Element(name = "orig", required = true)
    private NFOrigem origem;

    @Element(name = "CST", required = true)
    private NFNotaInfoImpostoTributacaoICMS situacaoTributaria;

    @Element(name = "modBC", required = true)
    private NFNotaInfoItemModalidadeBCICMS modalidadeBCICMS;

    @Element(name = "vBC", required = true)
    private String valorBaseCalculo;

    @Element(name = "pICMS", required = true)
    private String percentualAliquota;

    @Element(name = "vICMS", required = true)
    private String valorTributo;

    @Element(name = "modBCST", required = true)
    private NFNotaInfoItemModalidadeBCICMSST modalidadeBCICMSST;

    @Element(name = "pMVAST", required = false)
    private String percentualMargemValorICMSST;

    @Element(name = "pRedBCST", required = false)
    private String percentualReducaoBCICMSST;

    @Element(name = "vBCST", required = true)
    private String valorBCICMSST;

    @Element(name = "pICMSST", required = true)
    private String percentualAliquotaImpostoICMSST;

    @Element(name = "vICMSST", required = true)
    private String valorICMSST;

    @Element(name = "vBCFCPST", required = false)
    private String baseCalculoFcpSt;

    @Element(name = "pFCPST", required = false)
    private String percentualFcpSt;

    @Element(name = "vFCPST", required = false)
    private String valorFcpSt;

    public void setOrigem(final NFOrigem origem) {
        this.origem = origem;
    }

    public void setSituacaoTributaria(final NFNotaInfoImpostoTributacaoICMS situacaoTributaria) {
        this.situacaoTributaria = situacaoTributaria;
    }

    public void setModalidadeBCICMS(final NFNotaInfoItemModalidadeBCICMS modalidadeBCICMS) {
        this.modalidadeBCICMS = modalidadeBCICMS;
    }

    public void setValorBaseCalculo(final BigDecimal valorBaseCalculo) {
        this.valorBaseCalculo = BigDecimalParser.tamanho15Com2CasasDecimais(valorBaseCalculo,
                "Valor Base Calculo ICMS10 Item");
    }

    public void setPercentualAliquota(final BigDecimal aliquota) {
        this.percentualAliquota = BigDecimalParser.tamanho7ComAte4CasasDecimais(aliquota, "Aliquota ICMS10 Item");
    }

    public void setValorTributo(final BigDecimal valorTributo) {
        this.valorTributo = BigDecimalParser.tamanho15Com2CasasDecimais(valorTributo, "Valor Tributo ICMS10 Item");
    }

    public void setModalidadeBCICMSST(final NFNotaInfoItemModalidadeBCICMSST modalidadeBCICMSST) {
        this.modalidadeBCICMSST = modalidadeBCICMSST;
    }

    public void setPercentualMargemValorICMSST(final BigDecimal percentualMargemValorICMSST) {
        this.percentualMargemValorICMSST = BigDecimalParser.tamanho7ComAte4CasasDecimais(percentualMargemValorICMSST,
                "Percentual MVA ICMS ST ICMS10 Item");
    }

    public void setPercentualReducaoBCICMSST(final BigDecimal percentualReducaoBCICMSST) {
        this.percentualReducaoBCICMSST = BigDecimalParser.tamanho7ComAte4CasasDecimais(percentualReducaoBCICMSST,
                "Percentual Reducao BC ICMS ST ICMS10 Item");
    }

    public void setValorBCICMSST(final BigDecimal valorBCICMSST) {
        this.valorBCICMSST = BigDecimalParser.tamanho15Com2CasasDecimais(valorBCICMSST, "Valor BC ICMS ST ICMS10 Item");
    }

    public void setPercentualAliquotaImpostoICMSST(final BigDecimal aliquotaImpostoICMSST) {
        this.percentualAliquotaImpostoICMSST = BigDecimalParser.tamanho7ComAte4CasasDecimais(aliquotaImpostoICMSST,
                "Aliquota Imposto ICMS ST ICMS10 Item");
    }

    public void setValorICMSST(final BigDecimal valorICMSST) {
        this.valorICMSST = BigDecimalParser.tamanho15Com2CasasDecimais(valorICMSST, "Valor ICMS ST ICMS10 Item");
    }

    public void setBaseCalculoFcpSt(BigDecimal baseCalculoFcpSt) {
        this.baseCalculoFcpSt = BigDecimalParser.tamanho15Com2CasasDecimais(baseCalculoFcpSt,
                "Valor da Base de Cálculo do FCP");
    }

    public void setPercentualFcpSt(BigDecimal percentualFcpSt) {
        this.percentualFcpSt = BigDecimalParser.tamanho7ComAte4CasasDecimais(percentualFcpSt,
                "Percentual do FCP retido por Substituição Tributária");
    }

    public void setValorFcpSt(BigDecimal valorFcpSt) {
        this.valorFcpSt = BigDecimalParser.tamanho15Com2CasasDecimais(valorFcpSt,
                "Valor do FCP retido por Substituição Tributária");
    }

    public NFOrigem getOrigem() {
        return this.origem;
    }

    public NFNotaInfoImpostoTributacaoICMS getSituacaoTributaria() {
        return this.situacaoTributaria;
    }

    public NFNotaInfoItemModalidadeBCICMS getModalidadeBCICMS() {
        return this.modalidadeBCICMS;
    }

    public String getValorBaseCalculo() {
        return this.valorBaseCalculo;
    }

    public String getPercentualAliquota() {
        return this.percentualAliquota;
    }

    public String getValorTributo() {
        return this.valorTributo;
    }

    public NFNotaInfoItemModalidadeBCICMSST getModalidadeBCICMSST() {
        return this.modalidadeBCICMSST;
    }

    public String getPercentualMargemValorICMSST() {
        return this.percentualMargemValorICMSST;
    }

    public String getPercentualReducaoBCICMSST() {
        return this.percentualReducaoBCICMSST;
    }

    public String getValorBCICMSST() {
        return this.valorBCICMSST;
    }

    public String getPercentualAliquotaImpostoICMSST() {
        return this.percentualAliquotaImpostoICMSST;
    }

    public String getValorICMSST() {
        return this.valorICMSST;
    }

    public String getBaseCalculoFcpSt() {
        return baseCalculoFcpSt;
    }

    public String getPercentualFcpSt() {
        return percentualFcpSt;
    }

    public String getValorFcpSt() {
        return valorFcpSt;
    }
    
    
}
