package com.fincatto.documentofiscal.nfe310.webservices;

import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.nfe.NFeConfig;
import com.fincatto.documentofiscal.nfe.classes.cadastro.NFRetornoConsultaCadastro;
import com.fincatto.documentofiscal.nfe.classes.evento.NFEnviaEventoRetorno;
import com.fincatto.documentofiscal.nfe.classes.evento.downloadnf.NFDownloadNFeRetorno;
import com.fincatto.documentofiscal.nfe.classes.evento.inutilizacao.NFRetornoEventoInutilizacao;
import com.fincatto.documentofiscal.nfe.classes.evento.manifestacaodestinatario.NFTipoEventoManifestacaoDestinatario;
import com.fincatto.documentofiscal.nfe.classes.lote.consulta.NFLoteConsultaRetorno;
import com.fincatto.documentofiscal.nfe.classes.lote.envio.NFLoteEnvio;
import com.fincatto.documentofiscal.nfe.classes.lote.envio.NFLoteEnvioRetorno;
import com.fincatto.documentofiscal.nfe.classes.lote.envio.NFLoteEnvioRetornoDados;
import com.fincatto.documentofiscal.nfe.classes.lote.envio.NFLoteIndicadorProcessamento;
import com.fincatto.documentofiscal.nfe.classes.nota.consulta.NFNotaConsultaRetorno;
import com.fincatto.documentofiscal.nfe.classes.statusservico.consulta.NFStatusServicoConsultaRetorno;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import org.apache.commons.httpclient.protocol.Protocol;
import com.fincatto.documentofiscal.nfe.WebServicesNFeFacade;

public class WebServicesNFe310 implements WebServicesNFeFacade {

    private final WSLoteEnvio wsLoteEnvio;
    private final WSLoteConsulta wsLoteConsulta;
    private final WSStatusConsulta wsStatusConsulta;
    private final WSNotaConsulta wsNotaConsulta;
    private final WSCartaCorrecao wsCartaCorrecao;
    private final WSCancelamento wsCancelamento;
    private final WSConsultaCadastro wsConsultaCadastro;
    private final WSInutilizacao wsInutilizacao;
    private final WSManifestacaoDestinatario wSManifestacaoDestinatario;
    private final WSNotaDownload wsNotaDownload;

    public WebServicesNFe310(final NFeConfig config) throws IOException, KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
        
        Protocol.registerProtocol("https", new Protocol("https", new NFSocketFactory(config), 443));

        // inicia os servicos disponiveis da nfe
        this.wsLoteEnvio = new WSLoteEnvio(config);
        this.wsLoteConsulta = new WSLoteConsulta(config);
        this.wsStatusConsulta = new WSStatusConsulta(config);
        this.wsNotaConsulta = new WSNotaConsulta(config);
        this.wsCartaCorrecao = new WSCartaCorrecao(config);
        this.wsCancelamento = new WSCancelamento(config);
        this.wsConsultaCadastro = new WSConsultaCadastro(config);
        this.wsInutilizacao = new WSInutilizacao(config);
        this.wSManifestacaoDestinatario = new WSManifestacaoDestinatario(config);
        this.wsNotaDownload = new WSNotaDownload(config);
    }

    @Override
    public NFLoteEnvioRetornoDados enviaLote(final NFLoteEnvio lote) throws Exception {
        if (lote.getIndicadorProcessamento().equals(NFLoteIndicadorProcessamento.PROCESSAMENTO_SINCRONO)
                && lote.getNotas().size()>1) {
                throw new IllegalArgumentException("Apenas uma nota permitida no modo sincrono!");
        }
        return this.wsLoteEnvio.enviaLote(lote);
    }

    @Override
    public NFLoteEnvioRetorno enviaLoteAssinado(final String loteAssinadoXml, final DFModelo modelo) throws Exception {
        return this.wsLoteEnvio.enviaLoteAssinado(loteAssinadoXml, modelo);
    }

    @Override
    public NFLoteConsultaRetorno consultaLote(final String numeroRecibo, final DFModelo modelo) throws Exception {
        return this.wsLoteConsulta.consultaLote(numeroRecibo, modelo);
    }

    @Override
    public NFStatusServicoConsultaRetorno consultaStatus(final DFUnidadeFederativa uf, final DFModelo modelo) throws Exception {
        return this.wsStatusConsulta.consultaStatus(uf, modelo);
    }

    @Override
    public NFNotaConsultaRetorno consultaNota(final String chaveDeAcesso) throws Exception {
        return this.wsNotaConsulta.consultaNota(chaveDeAcesso);
    }

    @Override
    public NFEnviaEventoRetorno corrigeNota(final String chaveDeAcesso, final String textoCorrecao, final int numeroSequencialEvento) throws Exception {
        return this.wsCartaCorrecao.corrigeNota(chaveDeAcesso, textoCorrecao, numeroSequencialEvento);
    }

    @Override
    public NFEnviaEventoRetorno corrigeNotaAssinada(final String chave, final String eventoAssinadoXml) throws Exception {
        return this.wsCartaCorrecao.corrigeNotaAssinada(chave, eventoAssinadoXml);
    }

    @Override
    public NFEnviaEventoRetorno cancelaNota(final String chave, final String numeroProtocolo, final String motivo) throws Exception {
        return this.wsCancelamento.cancelaNota(chave, numeroProtocolo, motivo);
    }

    @Override
    public NFEnviaEventoRetorno cancelaNotaAssinada(final String chave, final String eventoAssinadoXml) throws Exception {
        return this.wsCancelamento.cancelaNotaAssinada(chave, eventoAssinadoXml);
    }

    @Override
    public NFRetornoEventoInutilizacao inutilizaNotaAssinada(final String eventoAssinadoXml, final DFModelo modelo) throws Exception {
        return this.wsInutilizacao.inutilizaNotaAssinada(eventoAssinadoXml, modelo);
    }

    @Override
    public NFRetornoEventoInutilizacao inutilizaNota(final int anoInutilizacaoNumeracao, final String cnpjEmitente, final String serie, final String numeroInicial, final String numeroFinal, final String justificativa, final DFModelo modelo) throws Exception {
        return this.wsInutilizacao.inutilizaNota(anoInutilizacaoNumeracao, cnpjEmitente, serie, numeroInicial, numeroFinal, justificativa, modelo);
    }

    @Override
    public NFRetornoConsultaCadastro consultaCadastro(final String cnpj, final DFUnidadeFederativa uf) throws Exception {
        return this.wsConsultaCadastro.consultaCadastro(cnpj, uf);
    }

    @Override
    public NFEnviaEventoRetorno manifestaDestinatarioNota(final String chave, final NFTipoEventoManifestacaoDestinatario tipoEvento, final String motivo, final String cnpj) throws Exception {
        return this.wSManifestacaoDestinatario.manifestaDestinatarioNota(chave, tipoEvento, motivo, cnpj);
    }

    @Override
    public NFEnviaEventoRetorno manifestaDestinatarioNotaAssinada(final String chave, final String eventoAssinadoXml) throws Exception {
        return this.wSManifestacaoDestinatario.manifestaDestinatarioNotaAssinada(chave, eventoAssinadoXml);
    }

    @Override
    public NFDownloadNFeRetorno downloadNota(final String cnpj, final String chave) throws Exception {
        return this.wsNotaDownload.downloadNota(cnpj, chave);
    }
}