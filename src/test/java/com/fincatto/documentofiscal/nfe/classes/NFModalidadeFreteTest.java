package com.fincatto.documentofiscal.nfe.classes;

import com.fincatto.documentofiscal.nfe.classes.NFModalidadeFrete;
import org.junit.Assert;
import org.junit.Test;

public class NFModalidadeFreteTest {

    @Test
    public void deveRepresentarOCodigoCorretamente() {
        Assert.assertEquals("0", NFModalidadeFrete.POR_CONTA_DO_EMITENTE.getCodigo());
        Assert.assertEquals("1", NFModalidadeFrete.POR_CONTA_DO_DESTINATARIO_REMETENTE.getCodigo());
        Assert.assertEquals("2", NFModalidadeFrete.POR_CONTA_DE_TERCEIROS.getCodigo());
        Assert.assertEquals("3", NFModalidadeFrete.PROPRIO_POR_CONTA_DO_REMETENTE.getCodigo());
        Assert.assertEquals("4", NFModalidadeFrete.PROPRIO_POR_CONTA_DO_DESTINATARIO.getCodigo());
        Assert.assertEquals("9", NFModalidadeFrete.SEM_FRETE.getCodigo());
    }
}
