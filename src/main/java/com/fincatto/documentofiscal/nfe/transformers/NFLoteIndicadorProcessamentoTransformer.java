package com.fincatto.documentofiscal.nfe.transformers;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.nfe.classes.lote.envio.NFLoteIndicadorProcessamento;

public class NFLoteIndicadorProcessamentoTransformer implements Transform<NFLoteIndicadorProcessamento> {

    @Override
    public NFLoteIndicadorProcessamento read(final String codigo) throws Exception {
        return NFLoteIndicadorProcessamento.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFLoteIndicadorProcessamento indicadorProcessamento) throws Exception {
        return indicadorProcessamento.getCodigo();
    }
}