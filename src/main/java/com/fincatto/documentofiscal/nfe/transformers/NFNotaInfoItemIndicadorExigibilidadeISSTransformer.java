package com.fincatto.documentofiscal.nfe.transformers;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.nfe.classes.nota.NFNotaInfoItemIndicadorExigibilidadeISS;

public class NFNotaInfoItemIndicadorExigibilidadeISSTransformer implements Transform<NFNotaInfoItemIndicadorExigibilidadeISS> {

    @Override
    public NFNotaInfoItemIndicadorExigibilidadeISS read(final String codigo) throws Exception {
        return NFNotaInfoItemIndicadorExigibilidadeISS.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFNotaInfoItemIndicadorExigibilidadeISS exigibilidadeISS) throws Exception {
        return exigibilidadeISS.getCodigo();
    }
}