package com.fincatto.documentofiscal.nfe.transformers;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.nfe.classes.NFTipoImpressao;

public class NFTipoImpressaoTransformer implements Transform<NFTipoImpressao> {

    @Override
    public NFTipoImpressao read(final String codigo) throws Exception {
        return NFTipoImpressao.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFTipoImpressao tipo) throws Exception {
        return tipo.getCodigo();
    }
}