package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.nfe.classes.nota.NFFormaPagamentoMoeda;
import com.fincatto.documentofiscal.nfe.classes.nota.NFNotaInfoDetalhamentoPagamento;
import com.fincatto.documentofiscal.nfe.FabricaDeObjetosFake;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

/**
 * Class: NFNotaInfoDetalhamentoPagamentoTest.
 *
 * <p>
 * Insert description here.
 * </p>
 *
 * <p>
 * History:<br><br>
 * - Walter Portugal - Oct 18, 2017: Criação do Arquivo<br>
 * <p>
 *
 * @author Walter Portugal
 * @since 1.0
 *
 */
public class NFNotaInfoDetalhamentoPagamentoTest {

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirValorPagamentoComTamanhoInvalido() {
        new NFNotaInfoDetalhamentoPagamento().setValorPagamento(new BigDecimal("10000000000000"));
    }

    @Test
    public void devePermitirCartaoNulo() {
        final NFNotaInfoDetalhamentoPagamento pagamento = new NFNotaInfoDetalhamentoPagamento();
        pagamento.setFormaPagamentoMoeda(NFFormaPagamentoMoeda.CARTAO_CREDITO);
        pagamento.setValorPagamento(new BigDecimal("999999999999.99"));
        pagamento.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirValorPagamentoNulo() {
        final NFNotaInfoDetalhamentoPagamento pagamento = new NFNotaInfoDetalhamentoPagamento();
        pagamento.setFormaPagamentoMoeda(NFFormaPagamentoMoeda.CARTAO_CREDITO);
        pagamento.setCartao(FabricaDeObjetosFake.getNFNotaInfoCartao());
        pagamento.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirFormaPagamentoMoedaNulo() {
        final NFNotaInfoDetalhamentoPagamento pagamento = new NFNotaInfoDetalhamentoPagamento();
        pagamento.setCartao(FabricaDeObjetosFake.getNFNotaInfoCartao());
        pagamento.setValorPagamento(new BigDecimal("999999999999.99"));
        pagamento.toString();
    }

    @Test
    public void deveGerarXMLDeAcordoComOPadraoEstabelecido() {
        final String xmlEsperado = "<NFNotaInfoDetalhamentoPagamento><tPag>03</tPag><vPag>999999999999.99</vPag><card><tpIntegra>1</tpIntegra><CNPJ>12345678901234</CNPJ><tBand>02</tBand><cAut>9ItpS1hBk3TyhjUB3I90</cAut></card></NFNotaInfoDetalhamentoPagamento>";
        Assert.assertEquals(xmlEsperado, FabricaDeObjetosFake.getNFNotaInfoDetalhamentoPagamento().toString());
    }
}
