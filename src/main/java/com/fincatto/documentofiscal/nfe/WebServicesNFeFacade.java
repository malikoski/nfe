package com.fincatto.documentofiscal.nfe;

import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.nfe.classes.cadastro.NFRetornoConsultaCadastro;
import com.fincatto.documentofiscal.nfe.classes.evento.NFEnviaEventoRetorno;
import com.fincatto.documentofiscal.nfe.classes.evento.downloadnf.NFDownloadNFeRetorno;
import com.fincatto.documentofiscal.nfe.classes.evento.inutilizacao.NFRetornoEventoInutilizacao;
import com.fincatto.documentofiscal.nfe.classes.evento.manifestacaodestinatario.NFTipoEventoManifestacaoDestinatario;
import com.fincatto.documentofiscal.nfe.classes.lote.consulta.NFLoteConsultaRetorno;
import com.fincatto.documentofiscal.nfe.classes.lote.envio.NFLoteEnvio;
import com.fincatto.documentofiscal.nfe.classes.lote.envio.NFLoteEnvioRetorno;
import com.fincatto.documentofiscal.nfe.classes.lote.envio.NFLoteEnvioRetornoDados;
import com.fincatto.documentofiscal.nfe.classes.nota.consulta.NFNotaConsultaRetorno;
import com.fincatto.documentofiscal.nfe.classes.statusservico.consulta.NFStatusServicoConsultaRetorno;
import com.fincatto.documentofiscal.nfe310.webservices.WebServicesNFe310;
import com.fincatto.documentofiscal.nfe400.webservices.WebServicesNFe400;

/**
 * Interface: WebServicesNFeFacade.
 *
 * <p>
 * Insert description here.
 * </p>
 *
 * <p>
 * History:<br><br>
 * - Walter Portugal - Oct 5, 2017: Criação do Arquivo<br>
 * <p>
 *
 * @author Walter Portugal
 * @since 1.0
 *
 */
public interface WebServicesNFeFacade {

    public static WebServicesNFeFacade wsNFeFactory(final NFeConfig config) throws Exception {
        switch (config.getVersao()) {
            case "3.10":
                return new WebServicesNFe310(config);
            case "4.00":
                return new WebServicesNFe400(config);
            default:
                throw new AssertionError();
        }
    }

    /**
     * Faz o envio de lote para a Sefaz
     *
     * @param lote o lote a ser enviado para a Sefaz
     * @return dados do lote retornado pelo webservice, alem do lote assinado
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFLoteEnvioRetornoDados enviaLote(final NFLoteEnvio lote) throws Exception;

    /**
     * Faz o envio assinado para a Sefaz de NF-e e NFC-e ATENCAO: Esse metodo deve ser utilizado para assinaturas A3
     *
     * @param loteAssinadoXml lote assinado no formato XML
     * @param modelo modelo da nota (NF-e ou NFC-e)
     * @return dados do lote retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFLoteEnvioRetorno enviaLoteAssinado(final String loteAssinadoXml, final DFModelo modelo) throws Exception;

    /**
     * Faz a consulta do lote na Sefaz (NF-e e NFC-e)
     *
     * @param numeroRecibo numero do recibo do processamento
     * @param modelo modelo da nota (NF-e ou NFC-e)
     * @return dados de consulta de lote retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFLoteConsultaRetorno consultaLote(final String numeroRecibo, final DFModelo modelo) throws Exception;

    /**
     * Faz a consulta de status responsavel pela UF
     *
     * @param uf uf UF que deseja consultar o status do sefaz responsavel
     * @param modelo modelo da nota (NF-e ou NFC-e)
     * @return dados da consulta de status retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFStatusServicoConsultaRetorno consultaStatus(final DFUnidadeFederativa uf, final DFModelo modelo) throws
            Exception;

    /**
     * Faz a consulta da nota
     *
     * @param chaveDeAcesso chave de acesso da nota
     * @return dados da consulta da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFNotaConsultaRetorno consultaNota(final String chaveDeAcesso) throws Exception;

    /**
     * Faz a correcao da nota
     *
     * @param chaveDeAcesso chave de acesso da nota
     * @param textoCorrecao texto de correcao
     * @param numeroSequencialEvento numero sequencial de evento, esse numero nao pode ser repetido!
     * @return dados da correcao da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFEnviaEventoRetorno corrigeNota(final String chaveDeAcesso, final String textoCorrecao,
            final int numeroSequencialEvento) throws Exception;

    /**
     * Faz a correcao da nota com o evento ja assinado ATENCAO: Esse metodo deve ser utilizado para assinaturas A3
     *
     * @param chave chave de acesso da nota
     * @param eventoAssinadoXml evento ja assinado em formato XML
     * @return dados da correcao da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFEnviaEventoRetorno corrigeNotaAssinada(final String chave, final String eventoAssinadoXml) throws Exception;

    /**
     * Faz o cancelamento da nota
     *
     * @param chave chave de acesso da nota
     * @param numeroProtocolo numero do protocolo da nota
     * @param motivo motivo do cancelamento
     * @return dados do cancelamento da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFEnviaEventoRetorno cancelaNota(final String chave, final String numeroProtocolo, final String motivo)
            throws Exception;

    /**
     * Faz o cancelamento da nota com evento ja assinado ATENCAO: Esse metodo deve ser utilizado para assinaturas A3
     *
     * @param chave chave de acesso da nota
     * @param eventoAssinadoXml evento ja assinado em formato XML
     * @return dados do cancelamento da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFEnviaEventoRetorno cancelaNotaAssinada(final String chave, final String eventoAssinadoXml) throws Exception;

    /**
     * Inutiliza a nota com o evento assinado ATENCAO: Esse metodo deve ser utilizado para assinaturas A3
     *
     * @param eventoAssinadoXml evento assinado em XML
     * @param modelo modelo da nota (NF-e ou NFC-e)
     * @return dados da inutilizacao da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFRetornoEventoInutilizacao inutilizaNotaAssinada(final String eventoAssinadoXml, final DFModelo modelo)
            throws Exception;

    /**
     * Inutiliza a nota
     *
     * @param anoInutilizacaoNumeracao ano de inutilizacao
     * @param cnpjEmitente CNPJ emitente da nota
     * @param serie serie da nota
     * @param numeroInicial numero inicial da nota
     * @param numeroFinal numero final da nota
     * @param justificativa justificativa da inutilizacao
     * @param modelo modelo da nota (NF-e ou NFC-e)
     * @return dados da inutilizacao da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFRetornoEventoInutilizacao inutilizaNota(final int anoInutilizacaoNumeracao, final String cnpjEmitente,
            final String serie, final String numeroInicial, final String numeroFinal, final String justificativa,
            final DFModelo modelo) throws Exception;

    /**
     * Realiza a consulta de cadastro de pessoa juridica com inscricao estadual
     *
     * @param cnpj CNPJ da pessoa juridica
     * @param uf UF da pessoa juridica
     * @return dados da consulta da pessoa juridica retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFRetornoConsultaCadastro consultaCadastro(final String cnpj, final DFUnidadeFederativa uf) throws Exception;

    /**
     * Faz a manifestação do destinatário da nota
     *
     * @param chave chave de acesso da nota
     * @param tipoEvento tipo do evento da manifestacao do destinatario
     * @param motivo motivo do cancelamento
     * @param cnpj cnpj do autor do evento
     * @return dados da manifestacao do destinatario da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFEnviaEventoRetorno manifestaDestinatarioNota(final String chave,
            final NFTipoEventoManifestacaoDestinatario tipoEvento, final String motivo, final String cnpj) throws
            Exception;

    /**
     * Faz a manifestação do destinatário da nota com evento ja assinado ATENCAO: Esse metodo deve ser utilizado para
     * assinaturas A3
     *
     * @param chave chave de acesso da nota
     * @param eventoAssinadoXml evento ja assinado em formato XML
     * @return dados da manifestacao do destinatario da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFEnviaEventoRetorno manifestaDestinatarioNotaAssinada(final String chave, final String eventoAssinadoXml)
            throws Exception;

    /**
     * Faz o download do xml da nota para um cnpj Informando até 10 chaves de acesso
     *
     * @param cnpj para quem foi emitida a nota
     * @param chave chave de acesso da nota
     * @return dados do download da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    NFDownloadNFeRetorno downloadNota(final String cnpj, final String chave) throws Exception;

}
