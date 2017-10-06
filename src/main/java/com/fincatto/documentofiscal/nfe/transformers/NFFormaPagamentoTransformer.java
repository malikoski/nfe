package com.fincatto.documentofiscal.nfe.transformers;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.nfe.classes.NFFormaPagamentoPrazo;

public class NFFormaPagamentoTransformer implements Transform<NFFormaPagamentoPrazo> {

    @Override
    public NFFormaPagamentoPrazo read(final String codigo) throws Exception {
        return NFFormaPagamentoPrazo.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFFormaPagamentoPrazo formaPagamento) throws Exception {
        return formaPagamento.getCodigo();
    }
}
