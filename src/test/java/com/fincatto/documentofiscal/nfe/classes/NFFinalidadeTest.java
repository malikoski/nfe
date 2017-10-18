package com.fincatto.documentofiscal.nfe.classes;

import org.junit.Assert;
import org.junit.Test;

import com.fincatto.documentofiscal.nfe.classes.NFFinalidade;

public class NFFinalidadeTest {

    @Test
    public void deveRepresentarOCodigoCorretamente() {
        Assert.assertEquals("1", NFFinalidade.NORMAL.getCodigo());
        Assert.assertEquals("2", NFFinalidade.COMPLEMENTAR.getCodigo());
        Assert.assertEquals("3", NFFinalidade.AJUSTE.getCodigo());
        Assert.assertEquals("4", NFFinalidade.DEVOLUCAO_OU_RETORNO.getCodigo());
    }
}