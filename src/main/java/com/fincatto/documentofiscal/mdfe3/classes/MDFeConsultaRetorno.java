package com.fincatto.documentofiscal.mdfe3.classes;

import org.joda.time.LocalDateTime;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.nfe.classes.NFProtocoloInfo;
import com.fincatto.documentofiscal.nfe.classes.lote.envio.NFLoteEnvioRetornoRecebimentoInfo;

/**
 * @Author Eldevan Nery Junior on 26/05/17.
 */
@Root(name = "retConsReciMDFe")
@Namespace(reference = "http://www.portalfiscal.inf.br/mdfe")
public class MDFeConsultaRetorno extends DFBase {

    @Attribute(name = "versao", required = true)
    private String versao;

    @Element(name = "tpAmb", required = true)
    private DFAmbiente ambiente;

    @Element(name = "verAplic", required = true)
    private String versaoAplicacao;

    @Element(name = "cStat", required = true)
    private String status;

    @Element(name = "xMotivo", required = true)
    private String motivo;

    @Element(name = "nRec", required = true)
    private String reciboConsultado;

    @Element(name = "cUF", required = true)
    private DFUnidadeFederativa uf;

    @Element(name = "dhRecbto", required = true)
    private LocalDateTime dataRecebimento;

    @Element(name = "infRec", required = false)
    private NFLoteEnvioRetornoRecebimentoInfo infoRecebimento;

    @Element(name = "protMDFe", required = false)
    private MDFProtocolo protocoloRecebimento;
    
    @Element(name = "infProt", required = false)
    private NFProtocoloInfo protocoloInfo;

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public void setVersaoAplicacao(final String versaoAplicacao) {
        this.versaoAplicacao = versaoAplicacao;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setMotivo(final String motivo) {
        this.motivo = motivo;
    }

    public void setUf(final DFUnidadeFederativa uf) {
        this.uf = uf;
    }

    public void setDataRecebimento(final LocalDateTime dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public NFLoteEnvioRetornoRecebimentoInfo getInfoRecebimento() {
        return this.infoRecebimento;
    }

    public void setInfoRecebimento(final NFLoteEnvioRetornoRecebimentoInfo infoRecebimento) {
        this.infoRecebimento = infoRecebimento;
    }

    public String getVersao() {
        return this.versao;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public String getVersaoAplicacao() {
        return this.versaoAplicacao;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public DFUnidadeFederativa getUf() {
        return this.uf;
    }

    public LocalDateTime getDataRecebimento() {
        return this.dataRecebimento;
    }

    public MDFProtocolo getProtocoloRecebimento() {
        return this.protocoloRecebimento;
    }

	public NFProtocoloInfo getProtocoloInfo() {
		return protocoloInfo;
	}

	public void setProtocoloInfo(NFProtocoloInfo protocoloInfo) {
		this.protocoloInfo = protocoloInfo;
	}

	public void setProtocoloRecebimentoSincrono(MDFProtocolo protocoloRecebimento) {
		this.protocoloRecebimento = protocoloRecebimento;
	}
    
    
}