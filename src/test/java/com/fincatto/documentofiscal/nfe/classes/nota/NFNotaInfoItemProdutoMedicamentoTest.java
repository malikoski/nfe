package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.nfe.classes.nota.NFNotaInfoItemProdutoMedicamento;
import java.math.BigDecimal;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class NFNotaInfoItemProdutoMedicamentoTest {

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirLoteComTamanhoInvalido() {
        try {
            new NFNotaInfoItemProdutoMedicamento().setLote("");
        } catch (final IllegalStateException e) {
            new NFNotaInfoItemProdutoMedicamento().setLote("yq50jVDZsvQVNuWoS45U1");
        }
    }

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirPrecoMaximoConsumidorComTamanhoInvalido() {
        new NFNotaInfoItemProdutoMedicamento().setPrecoMaximoConsumidor(new BigDecimal("10000000000000"));
    }

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirQuantidadeComTamanhoInvalido() {
        new NFNotaInfoItemProdutoMedicamento().setQuantidade(new BigDecimal("100000000"));
    }

    @Test
    public void devePermitirDataFabricacaoNulo() {//na NF-e 4.00 esse campo será retirado
        final NFNotaInfoItemProdutoMedicamento medicamento = new NFNotaInfoItemProdutoMedicamento();
        medicamento.setDataValidade(new LocalDate(2015, 1, 1));
        medicamento.setLote("yq50jVDZsvQVNuWoS45U");
        medicamento.setPrecoMaximoConsumidor(new BigDecimal("999999999999.99"));
        medicamento.setQuantidade(new BigDecimal("9999999.999"));
        medicamento.toString();
    }

    @Test
    public void devePermitirDataValidadeNulo() {//na NF-e 4.00 esse campo será retirado
        final NFNotaInfoItemProdutoMedicamento medicamento = new NFNotaInfoItemProdutoMedicamento();
        medicamento.setDataFabricacao(new LocalDate(2014, 1, 1));
        medicamento.setLote("yq50jVDZsvQVNuWoS45U");
        medicamento.setPrecoMaximoConsumidor(new BigDecimal("999999999999.99"));
        medicamento.setQuantidade(new BigDecimal("9999999.999"));
        medicamento.toString();
    }

    @Test
    public void devePermitirLoteNulo() {//na NF-e 4.00 esse campo será retirado
        final NFNotaInfoItemProdutoMedicamento medicamento = new NFNotaInfoItemProdutoMedicamento();
        medicamento.setDataFabricacao(new LocalDate(2014, 1, 1));
        medicamento.setDataValidade(new LocalDate(2015, 1, 1));
        medicamento.setPrecoMaximoConsumidor(new BigDecimal("999999999999.99"));
        medicamento.setQuantidade(new BigDecimal("9999999.999"));
        medicamento.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirPrecoMaximoConsumidorNulo() {
        final NFNotaInfoItemProdutoMedicamento medicamento = new NFNotaInfoItemProdutoMedicamento();
        medicamento.setDataFabricacao(new LocalDate(2014, 1, 1));
        medicamento.setDataValidade(new LocalDate(2015, 1, 1));
        medicamento.setLote("yq50jVDZsvQVNuWoS45U");
        medicamento.setQuantidade(new BigDecimal("9999999.999"));
        medicamento.toString();
    }

    @Test
    public void devePermitirQuantidadeNulo() {//na NF-e 4.00 esse campo será retirado
        final NFNotaInfoItemProdutoMedicamento medicamento = new NFNotaInfoItemProdutoMedicamento();
        medicamento.setDataFabricacao(new LocalDate(2014, 1, 1));
        medicamento.setDataValidade(new LocalDate(2015, 1, 1));
        medicamento.setLote("yq50jVDZsvQVNuWoS45U");
        medicamento.setPrecoMaximoConsumidor(new BigDecimal("999999999999.99"));
        medicamento.toString();
    }

    @Test
    public void deveGerarXMLDeAcordoComOPadraoEstabelecido() {
        final NFNotaInfoItemProdutoMedicamento medicamento = new NFNotaInfoItemProdutoMedicamento();
        medicamento.setDataFabricacao(new LocalDate(2014, 1, 1));
        medicamento.setDataValidade(new LocalDate(2015, 1, 1));
        medicamento.setLote("yq50jVDZsvQVNuWoS45U");
        medicamento.setPrecoMaximoConsumidor(new BigDecimal("999999999999.99"));
        medicamento.setQuantidade(new BigDecimal("9999999.999"));

        final String xmlEsperado = "<NFNotaInfoItemProdutoMedicamento><nLote>yq50jVDZsvQVNuWoS45U</nLote><qLote>9999999.999</qLote><dFab>2014-01-01</dFab><dVal>2015-01-01</dVal><vPMC>999999999999.99</vPMC></NFNotaInfoItemProdutoMedicamento>";
        Assert.assertEquals(xmlEsperado, medicamento.toString());
    }
}