package com.fincatto.documentofiscal.nfe.transformers;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.nfe.classes.NFNotaInfoItemModalidadeBCICMS;

public class NFNotaInfoItemModalidadeBCICMSTransformer implements Transform<NFNotaInfoItemModalidadeBCICMS> {

    @Override
    public NFNotaInfoItemModalidadeBCICMS read(final String codigo) throws Exception {
        return NFNotaInfoItemModalidadeBCICMS.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFNotaInfoItemModalidadeBCICMS modalidade) throws Exception {
        return modalidade.getCodigo();
    }
}