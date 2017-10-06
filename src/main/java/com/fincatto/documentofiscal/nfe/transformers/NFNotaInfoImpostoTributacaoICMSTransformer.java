package com.fincatto.documentofiscal.nfe.transformers;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.nfe.classes.NFNotaInfoImpostoTributacaoICMS;

public class NFNotaInfoImpostoTributacaoICMSTransformer implements Transform<NFNotaInfoImpostoTributacaoICMS> {

    @Override
    public NFNotaInfoImpostoTributacaoICMS read(final String icms) throws Exception {
        return NFNotaInfoImpostoTributacaoICMS.valueOfCodigo(icms);
    }

    @Override
    public String write(final NFNotaInfoImpostoTributacaoICMS icms) throws Exception {
        return icms.getCodigo();
    }
}