package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.nfe.validadores.BigDecimalParser;
import com.fincatto.documentofiscal.nfe.validadores.ListValidador;
import java.math.BigDecimal;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class NFNotaInfoPagamento extends DFBase {

    @ElementList(entry = "detPag", inline = true, required = false)//Passar a required true na versão 4.00 definitiva
    private List<NFNotaInfoDetalhamentoPagamento> detalhamentoPagamento;

    @Element(name = "tPag", required = false)//Retirar na versão 4.00
    private NFFormaPagamentoMoeda formaPagamentoMoeda;

    @Element(name = "vPag", required = false)//Retirar na versão 4.00
    private String valorPagamento;

    @Element(name = "card", required = false)//Retirar na versão 4.00
    private NFNotaInfoCartao cartao;

    @Element(name = "vTroco", required = false)
    private String valorTroco;

    public void setCartao(final NFNotaInfoCartao cartao) {
        this.cartao = cartao;
    }

    public void setFormaPagamentoMoeda(final NFFormaPagamentoMoeda formaPagamentoMoeda) {
        this.formaPagamentoMoeda = formaPagamentoMoeda;
    }

    public void setValorPagamento(final BigDecimal valorPagamento) {
        this.valorPagamento = BigDecimalParser.tamanho15Com2CasasDecimais(valorPagamento, "Valor Pagamento");
    }

    public void setValorTroco(BigDecimal valorTroco) {
        this.valorTroco = BigDecimalParser.tamanho15Com2CasasDecimais(valorTroco, "Valor do Troco");
    }

    public void setDetalhamentoPagamento(final List<NFNotaInfoDetalhamentoPagamento> detalhamentoPagamento) {
        ListValidador.tamanho100(detalhamentoPagamento, "Grupo de detalhamento da forma de pagamento");
        this.detalhamentoPagamento = detalhamentoPagamento;
    }

    public NFFormaPagamentoMoeda getFormaPagamentoMoeda() {
        return this.formaPagamentoMoeda;
    }

    public String getValorPagamento() {
        return this.valorPagamento;
    }

    public NFNotaInfoCartao getCartao() {
        return this.cartao;
    }

    public String getValorTroco() {
        return valorTroco;
    }

    public List<NFNotaInfoDetalhamentoPagamento> getDetalhamentoPagamento() {
        return detalhamentoPagamento;
    }

}
