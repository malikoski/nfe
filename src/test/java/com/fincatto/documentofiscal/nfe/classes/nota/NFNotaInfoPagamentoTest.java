package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.nfe.classes.nota.NFFormaPagamentoMoeda;
import com.fincatto.documentofiscal.nfe.classes.nota.NFNotaInfoPagamento;
import com.fincatto.documentofiscal.nfe.FabricaDeObjetosFake;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class NFNotaInfoPagamentoTest {

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirValorPagamentoComTamanhoInvalido() {
        new NFNotaInfoPagamento().setValorPagamento(new BigDecimal("10000000000000"));
    }

    @Test
    public void devePermitirCartaoNulo() {
        final NFNotaInfoPagamento pagamento = new NFNotaInfoPagamento();
        pagamento.setFormaPagamentoMoeda(NFFormaPagamentoMoeda.CARTAO_CREDITO);
        pagamento.setValorPagamento(new BigDecimal("999999999999.99"));
        pagamento.toString();
    }

    @Test
    public void devePermitirValorPagamentoNulo() {
        final NFNotaInfoPagamento pagamento = new NFNotaInfoPagamento();
        pagamento.setFormaPagamentoMoeda(NFFormaPagamentoMoeda.CARTAO_CREDITO);
        pagamento.setCartao(FabricaDeObjetosFake.getNFNotaInfoCartao());
        pagamento.toString();
    }

    @Test
    public void devePermitirFormaPagamentoMoedaNulo() {
        final NFNotaInfoPagamento pagamento = new NFNotaInfoPagamento();
        pagamento.setCartao(FabricaDeObjetosFake.getNFNotaInfoCartao());
        pagamento.setValorPagamento(new BigDecimal("999999999999.99"));
        pagamento.toString();
    }

    @Test
    public void deveGerarXMLDeAcordoComOPadraoEstabelecido() {
        final String xmlEsperado = "<NFNotaInfoPagamento><detPag><tPag>03</tPag><vPag>999999999999.99</vPag><card><tpIntegra>1</tpIntegra><CNPJ>12345678901234</CNPJ><tBand>02</tBand><cAut>9ItpS1hBk3TyhjUB3I90</cAut></card></detPag><vTroco>999999999999.99</vTroco></NFNotaInfoPagamento>";
        Assert.assertEquals(xmlEsperado, FabricaDeObjetosFake.getNFNotaInfoPagamento().toString());
    }
}