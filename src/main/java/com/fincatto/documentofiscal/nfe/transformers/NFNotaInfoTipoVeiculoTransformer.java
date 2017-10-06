package com.fincatto.documentofiscal.nfe.transformers;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.nfe.classes.NFNotaInfoTipoVeiculo;

public class NFNotaInfoTipoVeiculoTransformer implements Transform<NFNotaInfoTipoVeiculo> {

    @Override
    public NFNotaInfoTipoVeiculo read(final String codigoTipoVeiculo) throws Exception {
        return NFNotaInfoTipoVeiculo.valueOfCodigo(codigoTipoVeiculo);
    }

    @Override
    public String write(final NFNotaInfoTipoVeiculo tipoVeiculo) throws Exception {
        return tipoVeiculo.getCodigo();
    }
}