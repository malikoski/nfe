package com.fincatto.documentofiscal.nfe.transformers;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.nfe.classes.NFProcessoEmissor;

public class NFProgramaEmissorTransformer implements Transform<NFProcessoEmissor> {

    @Override
    public NFProcessoEmissor read(final String codigo) throws Exception {
        return NFProcessoEmissor.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFProcessoEmissor tipo) throws Exception {
        return tipo.getCodigo();
    }
}