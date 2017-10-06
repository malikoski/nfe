package com.fincatto.documentofiscal.nfe.transformers;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.nfe.classes.NFProdutoCompoeValorNota;

public class NFProdutoCompoeValorNotaTransformer implements Transform<NFProdutoCompoeValorNota> {

    @Override
    public NFProdutoCompoeValorNota read(final String codigo) throws Exception {
        return NFProdutoCompoeValorNota.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFProdutoCompoeValorNota tipo) throws Exception {
        return tipo.getCodigo();
    }
}