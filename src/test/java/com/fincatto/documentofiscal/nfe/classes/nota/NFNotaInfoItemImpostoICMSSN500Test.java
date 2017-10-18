package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.nfe.classes.NFNotaSituacaoOperacionalSimplesNacional;
import com.fincatto.documentofiscal.nfe.classes.NFOrigem;
import com.fincatto.documentofiscal.nfe.classes.nota.NFNotaInfoItemImpostoICMSSN500;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class NFNotaInfoItemImpostoICMSSN500Test {

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirValorICMSSTRetidoTamanhoInvalido() {
        new NFNotaInfoItemImpostoICMSSN500().setValorICMSSTRetido(new BigDecimal("10000000000000"));
    }

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirValorBCICMSSTRetidoTamanhoInvalido() {
        new NFNotaInfoItemImpostoICMSSN500().setValorBCICMSSTRetido(new BigDecimal("10000000000000"));
    }

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirBaseCalculoFcpComTamanhoInvalido() {
        new NFNotaInfoItemImpostoICMSSN500().setBaseCalculoFcpStRetido(new BigDecimal("10000000000000"));
    }

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirPercentualFcpStComTamanhoInvalido() {
        new NFNotaInfoItemImpostoICMSSN500().setPercentualFcpStRetido(new BigDecimal("10000000000000"));
    }

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirValorFcpStComTamanhoInvalido() {
        new NFNotaInfoItemImpostoICMSSN500().setValorFcpStRetido(new BigDecimal("10000000000000"));
    }

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirAliquotaConsumidorFinalComTamanhoInvalido() {
        new NFNotaInfoItemImpostoICMSSN500().setAliquotaConsumidorFinal(new BigDecimal("10000000000000"));
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirOrigemSNNulo() {
        final NFNotaInfoItemImpostoICMSSN500 icms500 = new NFNotaInfoItemImpostoICMSSN500();
        icms500.setSituacaoOperacaoSN(NFNotaSituacaoOperacionalSimplesNacional.IMUNE);
        icms500.setValorBCICMSSTRetido(new BigDecimal("999999999999.99"));
        icms500.setValorICMSSTRetido(new BigDecimal("999999999999.99"));
        icms500.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirSituacaoOperacaoSNNulo() {
        final NFNotaInfoItemImpostoICMSSN500 icms500 = new NFNotaInfoItemImpostoICMSSN500();
        icms500.setOrigem(NFOrigem.NACIONAL);
        icms500.setValorBCICMSSTRetido(new BigDecimal("999999999999.99"));
        icms500.setValorICMSSTRetido(new BigDecimal("999999999999.99"));
        icms500.toString();
    }

    @Test
    public void devePermitirValorBCICMSSTRetidoNulo() {
        final NFNotaInfoItemImpostoICMSSN500 icms500 = new NFNotaInfoItemImpostoICMSSN500();
        icms500.setOrigem(NFOrigem.NACIONAL);
        icms500.setSituacaoOperacaoSN(NFNotaSituacaoOperacionalSimplesNacional.IMUNE);
        icms500.setValorICMSSTRetido(new BigDecimal("999999999999.99"));
        icms500.toString();
    }

    @Test
    public void devePermitirValorICMSSTRetidoNulo() {
        final NFNotaInfoItemImpostoICMSSN500 icms500 = new NFNotaInfoItemImpostoICMSSN500();
        icms500.setOrigem(NFOrigem.NACIONAL);
        icms500.setSituacaoOperacaoSN(NFNotaSituacaoOperacionalSimplesNacional.IMUNE);
        icms500.setValorBCICMSSTRetido(new BigDecimal("999999999999.99"));
        icms500.toString();
    }

    @Test
    public void deveGerarXMLDeAcordoComOPadraoEstabelecido() {
        final NFNotaInfoItemImpostoICMSSN500 icms500 = new NFNotaInfoItemImpostoICMSSN500();
        icms500.setOrigem(NFOrigem.NACIONAL);
        icms500.setSituacaoOperacaoSN(NFNotaSituacaoOperacionalSimplesNacional.IMUNE);
        icms500.setValorBCICMSSTRetido(new BigDecimal("999999999999.99"));
        icms500.setValorICMSSTRetido(new BigDecimal("999999999999.99"));
        icms500.setValorFcpStRetido(new BigDecimal("999999999999.99"));
        icms500.setPercentualFcpStRetido(new BigDecimal("99.99"));
        icms500.setBaseCalculoFcpStRetido(new BigDecimal("999999999999.99"));
        icms500.setAliquotaConsumidorFinal(new BigDecimal("99.99"));

        final String xmlEsperado
                = "<NFNotaInfoItemImpostoICMSSN500><orig>0</orig><CSOSN>300</CSOSN><vBCSTRet>999999999999.99</vBCSTRet><vICMSSTRet>999999999999.99</vICMSSTRet><vBCFCPSTRet>999999999999.99</vBCFCPSTRet><pFCPSTRet>99.99</pFCPSTRet><vFCPSTRet>999999999999.99</vFCPSTRet><pST>99.99</pST></NFNotaInfoItemImpostoICMSSN500>";
        Assert.assertEquals(xmlEsperado, icms500.toString());
    }
}
