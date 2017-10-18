package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.nfe.classes.NFNotaInfoImpostoTributacaoICMS;
import com.fincatto.documentofiscal.nfe.classes.NFNotaInfoItemModalidadeBCICMSST;
import com.fincatto.documentofiscal.nfe.classes.NFNotaMotivoDesoneracaoICMS;
import com.fincatto.documentofiscal.nfe.classes.NFOrigem;
import com.fincatto.documentofiscal.nfe.validadores.BigDecimalParser;
import java.math.BigDecimal;
import org.simpleframework.xml.Element;

public class NFNotaInfoItemImpostoICMS30 extends DFBase {
    @Element(name = "orig", required = true)
    private NFOrigem origem;

    @Element(name = "CST", required = true)
    private NFNotaInfoImpostoTributacaoICMS situacaoTributaria;

    @Element(name = "modBCST", required = true)
    private NFNotaInfoItemModalidadeBCICMSST modalidadeBCICMSST;

    @Element(name = "pMVAST", required = false)
    private String percentualMargemValorAdicionadoICMSST;

    @Element(name = "pRedBCST", required = false)
    private String percentualReducaoBCICMSST;

    @Element(name = "vBCST", required = true)
    private String valorBCICMSST;

    @Element(name = "pICMSST", required = true)
    private String percentualAliquotaImpostoICMSST;

    @Element(name = "vICMSST", required = true)
    private String valorImpostoICMSST;

    @Element(name = "vICMSDeson", required = false)
    private String valorICMSDesoneracao;

    @Element(name = "motDesICMS", required = false)
    private NFNotaMotivoDesoneracaoICMS desoneracao;

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

    public void setModalidadeBCICMSST(final NFNotaInfoItemModalidadeBCICMSST modalidadeBCICMSST) {
        this.modalidadeBCICMSST = modalidadeBCICMSST;
    }

    public void setPercentualMargemValorAdicionadoICMSST(final BigDecimal percentualMargemValorAdicionadoICMSST) {
        this.percentualMargemValorAdicionadoICMSST = BigDecimalParser.tamanho7ComAte4CasasDecimais(percentualMargemValorAdicionadoICMSST, "Percentual Margem Valor Adicionado ICMS ST ICMS30 Item");
    }

    public void setPercentualReducaoBCICMSST(final BigDecimal percentualReducaoBCICMSST) {
        this.percentualReducaoBCICMSST = BigDecimalParser.tamanho7ComAte4CasasDecimais(percentualReducaoBCICMSST, "Percentual Reducao BC ICMS ST ICMS30 Item");
    }

    public void setValorBCICMSST(final BigDecimal valorBCICMSST) {
        this.valorBCICMSST = BigDecimalParser.tamanho15Com2CasasDecimais(valorBCICMSST, "Valor BC ICMS ST ICMS30 Item");
    }

    public void setPercentualAliquotaImpostoICMSST(final BigDecimal aliquotaImpostoICMSST) {
        this.percentualAliquotaImpostoICMSST = BigDecimalParser.tamanho7ComAte4CasasDecimais(aliquotaImpostoICMSST, "Aliquota Imposto ICMS ST ICMS30 Item");
    }

    public void setValorImpostoICMSST(final BigDecimal valorImpostoICMSST) {
        this.valorImpostoICMSST = BigDecimalParser.tamanho15Com2CasasDecimais(valorImpostoICMSST, "Valor Imposto ICMS ST ICMS30 Item");
    }

    public void setDesoneracao(final NFNotaMotivoDesoneracaoICMS nfNotaMotivoDesoneracaoICMS) {
        this.desoneracao = nfNotaMotivoDesoneracaoICMS;
    }

    public void setValorICMSDesoneracao(final BigDecimal valorICMSDesoneracao) {
        this.valorICMSDesoneracao = BigDecimalParser.tamanho15Com2CasasDecimais(valorICMSDesoneracao, "Valor ICMS Desoneracao");
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

    public NFNotaInfoItemModalidadeBCICMSST getModalidadeBCICMSST() {
        return this.modalidadeBCICMSST;
    }

    public String getPercentualMargemValorAdicionadoICMSST() {
        return this.percentualMargemValorAdicionadoICMSST;
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

    public String getValorImpostoICMSST() {
        return this.valorImpostoICMSST;
    }

    public String getValorICMSDesoneracao() {
        return this.valorICMSDesoneracao;
    }

    public NFNotaMotivoDesoneracaoICMS getDesoneracao() {
        return this.desoneracao;
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