package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.nfe.classes.NFNotaInfoImpostoTributacaoICMS;
import com.fincatto.documentofiscal.nfe.classes.NFNotaInfoItemModalidadeBCICMS;
import com.fincatto.documentofiscal.nfe.classes.NFOrigem;
import com.fincatto.documentofiscal.nfe.classes.nota.NFNotaInfoItemImpostoICMS00;
import com.fincatto.documentofiscal.nfe.FabricaDeObjetosFake;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class NFNotaInfoItemImpostoICMS00Test {

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirPercentualAliquotaComTamanhoInvalido() {
        new NFNotaInfoItemImpostoICMS00().setPercentualAliquota(new BigDecimal("1000"));
    }

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirValorBaseCalculoComTamanhoInvalido() {
        new NFNotaInfoItemImpostoICMS00().setValorBaseCalculo(new BigDecimal("10000000000000"));
    }

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirValorTributoComTamanhoInvalido() {
        new NFNotaInfoItemImpostoICMS00().setValorTributo(new BigDecimal("10000000000000"));
    }
    
    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirPercentualFcpComTamanhoInvalido() {
        new NFNotaInfoItemImpostoICMS00().setPercentualFcp(new BigDecimal("10000000000000"));
    }

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirValorFcpComTamanhoInvalido() {
        new NFNotaInfoItemImpostoICMS00().setValorFcp(new BigDecimal("10000000000000"));
    }
    
    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirModalidadeBaseCalculoNulo() {
        final NFNotaInfoItemImpostoICMS00 icms00 = new NFNotaInfoItemImpostoICMS00();
        icms00.setOrigem(NFOrigem.NACIONAL);
        icms00.setPercentualAliquota(new BigDecimal("99.99"));
        icms00.setSituacaoTributaria(NFNotaInfoImpostoTributacaoICMS.TRIBUTACAO_INTEGRALMENTE);
        icms00.setValorBaseCalculo(new BigDecimal("999999999999.99"));
        icms00.setValorTributo(new BigDecimal("999999999999.99"));
        icms00.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirOrigemNulo() {
        final NFNotaInfoItemImpostoICMS00 icms00 = new NFNotaInfoItemImpostoICMS00();
        icms00.setModalidadeBCICMS(NFNotaInfoItemModalidadeBCICMS.PAUTA);
        icms00.setPercentualAliquota(new BigDecimal("99.99"));
        icms00.setSituacaoTributaria(NFNotaInfoImpostoTributacaoICMS.TRIBUTACAO_INTEGRALMENTE);
        icms00.setValorBaseCalculo(new BigDecimal("999999999999.99"));
        icms00.setValorTributo(new BigDecimal("999999999999.99"));
        icms00.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirPercentualAliquotaNulo() {
        final NFNotaInfoItemImpostoICMS00 icms00 = new NFNotaInfoItemImpostoICMS00();
        icms00.setModalidadeBCICMS(NFNotaInfoItemModalidadeBCICMS.PAUTA);
        icms00.setOrigem(NFOrigem.NACIONAL);
        icms00.setSituacaoTributaria(NFNotaInfoImpostoTributacaoICMS.TRIBUTACAO_INTEGRALMENTE);
        icms00.setValorBaseCalculo(new BigDecimal("999999999999.99"));
        icms00.setValorTributo(new BigDecimal("999999999999.99"));
        icms00.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirSituacaoTributariaCalculoNulo() {
        final NFNotaInfoItemImpostoICMS00 icms00 = new NFNotaInfoItemImpostoICMS00();
        icms00.setModalidadeBCICMS(NFNotaInfoItemModalidadeBCICMS.PAUTA);
        icms00.setOrigem(NFOrigem.NACIONAL);
        icms00.setPercentualAliquota(new BigDecimal("99.99"));
        icms00.setValorBaseCalculo(new BigDecimal("999999999999.99"));
        icms00.setValorTributo(new BigDecimal("999999999999.99"));
        icms00.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirValorBaseCalculoNulo() {
        final NFNotaInfoItemImpostoICMS00 icms00 = new NFNotaInfoItemImpostoICMS00();
        icms00.setModalidadeBCICMS(NFNotaInfoItemModalidadeBCICMS.PAUTA);
        icms00.setOrigem(NFOrigem.NACIONAL);
        icms00.setPercentualAliquota(new BigDecimal("99.99"));
        icms00.setSituacaoTributaria(NFNotaInfoImpostoTributacaoICMS.TRIBUTACAO_INTEGRALMENTE);
        icms00.setValorTributo(new BigDecimal("999999999999.99"));
        icms00.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirValorTributoNulo() {
        final NFNotaInfoItemImpostoICMS00 icms00 = new NFNotaInfoItemImpostoICMS00();
        icms00.setModalidadeBCICMS(NFNotaInfoItemModalidadeBCICMS.PAUTA);
        icms00.setOrigem(NFOrigem.NACIONAL);
        icms00.setPercentualAliquota(new BigDecimal("99.99"));
        icms00.setSituacaoTributaria(NFNotaInfoImpostoTributacaoICMS.TRIBUTACAO_INTEGRALMENTE);
        icms00.setValorBaseCalculo(new BigDecimal("999999999999.99"));
        icms00.toString();
    }
    
    @Test
    public void devePermitirValorFcpNulo() {
        final NFNotaInfoItemImpostoICMS00 icms00 = new NFNotaInfoItemImpostoICMS00();
        icms00.setModalidadeBCICMS(NFNotaInfoItemModalidadeBCICMS.PAUTA);
        icms00.setOrigem(NFOrigem.NACIONAL);
        icms00.setPercentualAliquota(new BigDecimal("99.99"));
        icms00.setSituacaoTributaria(NFNotaInfoImpostoTributacaoICMS.TRIBUTACAO_INTEGRALMENTE);
        icms00.setValorBaseCalculo(new BigDecimal("999999999999.99"));
        icms00.setValorTributo(new BigDecimal("999999999999.99"));
        icms00.setPercentualFcp(new BigDecimal("99.99"));
        icms00.toString();
    }

    @Test
    public void devePermitirPercentualFcpNulo() {
        final NFNotaInfoItemImpostoICMS00 icms00 = new NFNotaInfoItemImpostoICMS00();
        icms00.setModalidadeBCICMS(NFNotaInfoItemModalidadeBCICMS.PAUTA);
        icms00.setOrigem(NFOrigem.NACIONAL);
        icms00.setPercentualAliquota(new BigDecimal("99.99"));
        icms00.setSituacaoTributaria(NFNotaInfoImpostoTributacaoICMS.TRIBUTACAO_INTEGRALMENTE);
        icms00.setValorBaseCalculo(new BigDecimal("999999999999.99"));
        icms00.setValorTributo(new BigDecimal("999999999999.99"));
        icms00.setValorFcp(new BigDecimal("999999999999.99"));
        icms00.toString();
    }

    @Test
    public void deveGerarXMLDeAcordoComOPadraoEstabelecido() {
        final String xmlEsperado = "<NFNotaInfoItemImpostoICMS00><orig>0</orig><CST>00</CST><modBC>1</modBC><vBC>999999999999.99</vBC><pICMS>99.99</pICMS><vICMS>999999999999.99</vICMS><pFCP>99.99</pFCP><vFCP>999999999999.99</vFCP></NFNotaInfoItemImpostoICMS00>";
        Assert.assertEquals(xmlEsperado, FabricaDeObjetosFake.getNFNotaInfoItemImpostoICMS00().toString());
    }
}