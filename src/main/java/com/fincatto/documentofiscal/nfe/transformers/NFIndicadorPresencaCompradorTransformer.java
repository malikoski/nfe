package com.fincatto.documentofiscal.nfe.transformers;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.nfe.classes.nota.NFIndicadorPresencaComprador;

public class NFIndicadorPresencaCompradorTransformer implements Transform<NFIndicadorPresencaComprador> {

    @Override
    public NFIndicadorPresencaComprador read(final String codigo) throws Exception {
        return NFIndicadorPresencaComprador.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFIndicadorPresencaComprador indicadorPresencaComprador) throws Exception {
        return indicadorPresencaComprador.getCodigo();
    }
}