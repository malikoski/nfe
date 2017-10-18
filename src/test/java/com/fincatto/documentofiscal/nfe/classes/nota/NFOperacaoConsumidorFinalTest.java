package com.fincatto.documentofiscal.nfe.classes.nota;

import org.junit.Assert;
import org.junit.Test;

import com.fincatto.documentofiscal.nfe.classes.nota.NFOperacaoConsumidorFinal;
import com.fincatto.documentofiscal.nfe.classes.nota.NFOperadoraCartao;

public class NFOperacaoConsumidorFinalTest {

    @Test
    public void deveObterTipoApartirDoSeuCodigo() {
        Assert.assertEquals(NFOperacaoConsumidorFinal.NAO, NFOperacaoConsumidorFinal.valueOfCodigo("0"));
        Assert.assertEquals(NFOperacaoConsumidorFinal.SIM, NFOperacaoConsumidorFinal.valueOfCodigo("1"));
        Assert.assertNull(NFOperadoraCartao.valueOfCodigo("2"));
    }

    @Test
    public void deveRepresentarOCodigoCorretamente() {
        Assert.assertEquals("0", NFOperacaoConsumidorFinal.NAO.getCodigo());
        Assert.assertEquals("1", NFOperacaoConsumidorFinal.SIM.getCodigo());
    }

    @Test
    public void deveObterStringficadoCorretamente() {
        Assert.assertEquals("0 - N\u00e3o", NFOperacaoConsumidorFinal.NAO.toString());
    }
}