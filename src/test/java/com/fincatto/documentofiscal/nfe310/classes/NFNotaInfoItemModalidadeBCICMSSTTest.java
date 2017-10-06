package com.fincatto.documentofiscal.nfe310.classes;

import org.junit.Assert;
import org.junit.Test;

import com.fincatto.documentofiscal.nfe.classes.NFNotaInfoItemModalidadeBCICMSST;

public class NFNotaInfoItemModalidadeBCICMSSTTest {

    @Test
    public void deveRepresentarOCodigoCorretamente() {
        Assert.assertEquals("0", NFNotaInfoItemModalidadeBCICMSST.PRECO_TABELADO.getCodigo());
        Assert.assertEquals("1", NFNotaInfoItemModalidadeBCICMSST.LISTA_NEGATIVA.getCodigo());
        Assert.assertEquals("2", NFNotaInfoItemModalidadeBCICMSST.LISTA_POSITIVA.getCodigo());
        Assert.assertEquals("3", NFNotaInfoItemModalidadeBCICMSST.LISTA_NEUTRA.getCodigo());
        Assert.assertEquals("4", NFNotaInfoItemModalidadeBCICMSST.MARGEM_VALOR_AGREGADO.getCodigo());
        Assert.assertEquals("5", NFNotaInfoItemModalidadeBCICMSST.PAUTA.getCodigo());
    }
}