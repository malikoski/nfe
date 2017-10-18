package com.fincatto.documentofiscal.nfe.classes.nota;

import com.fincatto.documentofiscal.nfe.classes.nota.NFNotaInfoItemProdutoRastreavel;
import java.math.BigDecimal;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

/**
 * Class: NFNotaInfoItemProdutoRastreavelTest.
 *
 * <p>
 * Insert description here.
 * </p>
 *
 * <p>
 * History:<br><br>
 * - Walter Portugal - Oct 17, 2017: Criação do Arquivo<br>
 * <p>
 *
 * @author Walter Portugal
 * @since 1.0
 *
 */
public class NFNotaInfoItemProdutoRastreavelTest {

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirLoteComTamanhoInvalido() {
        try {
            new NFNotaInfoItemProdutoRastreavel().setLote("");
        } catch (final IllegalStateException e) {
            new NFNotaInfoItemProdutoRastreavel().setLote("yq50jVDZsvQVNuWoS45U1");
        }
    }

    @Test(expected = NumberFormatException.class)
    public void naoDevePermitirQuantidadeComTamanhoInvalido() {
        new NFNotaInfoItemProdutoRastreavel().setQuantidade(new BigDecimal("100000000"));
    }
    
    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirDataFabricacaoNulo() {
        final NFNotaInfoItemProdutoRastreavel rastreavel = new NFNotaInfoItemProdutoRastreavel();
        rastreavel.setDataValidade(new LocalDate(2015, 1, 1));
        rastreavel.setLote("yq50jVDZsvQVNuWoS45U");
        rastreavel.setQuantidade(new BigDecimal("9999999.999"));
        rastreavel.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirDataValidadeNulo() {
        final NFNotaInfoItemProdutoRastreavel rastreavel = new NFNotaInfoItemProdutoRastreavel();
        rastreavel.setDataFabricacao(new LocalDate(2014, 1, 1));
        rastreavel.setLote("yq50jVDZsvQVNuWoS45U");        
        rastreavel.setQuantidade(new BigDecimal("9999999.999"));
        rastreavel.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirLoteNulo() {
        final NFNotaInfoItemProdutoRastreavel rastreavel = new NFNotaInfoItemProdutoRastreavel();
        rastreavel.setDataFabricacao(new LocalDate(2014, 1, 1));
        rastreavel.setDataValidade(new LocalDate(2015, 1, 1));
        rastreavel.setQuantidade(new BigDecimal("9999999.999"));
        rastreavel.toString();
    }
    
    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirQuantidadeNulo() {
        final NFNotaInfoItemProdutoRastreavel rastreavel = new NFNotaInfoItemProdutoRastreavel();
        rastreavel.setDataFabricacao(new LocalDate(2014, 1, 1));
        rastreavel.setDataValidade(new LocalDate(2015, 1, 1));
        rastreavel.setLote("yq50jVDZsvQVNuWoS45U");
        rastreavel.toString();
    }

    @Test
    public void deveGerarXMLDeAcordoComOPadraoEstabelecido() {
        final NFNotaInfoItemProdutoRastreavel rastreavel = new NFNotaInfoItemProdutoRastreavel();
        rastreavel.setDataFabricacao(new LocalDate(2014, 1, 1));
        rastreavel.setDataValidade(new LocalDate(2015, 1, 1));
        rastreavel.setLote("yq50jVDZsvQVNuWoS45U");
        rastreavel.setQuantidade(new BigDecimal("9999999.999"));

        final String xmlEsperado = "<NFNotaInfoItemProdutoRastreavel><nLote>yq50jVDZsvQVNuWoS45U</nLote><qLote>9999999.999</qLote><dFab>2014-01-01</dFab><dVal>2015-01-01</dVal></NFNotaInfoItemProdutoRastreavel>";
        Assert.assertEquals(xmlEsperado, rastreavel.toString());
    }
}
