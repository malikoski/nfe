package com.fincatto.documentofiscal.nfe.transformers;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.nfe.classes.nota.NFOperadoraCartao;

public class NFOperadoraCartaoTransformer implements Transform<NFOperadoraCartao> {

    @Override
    public NFOperadoraCartao read(final String codigo) throws Exception {
        return NFOperadoraCartao.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFOperadoraCartao operadoraCartao) throws Exception {
        return operadoraCartao.getCodigo();
    }
}