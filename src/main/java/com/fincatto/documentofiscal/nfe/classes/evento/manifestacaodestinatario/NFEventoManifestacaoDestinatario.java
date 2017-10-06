package com.fincatto.documentofiscal.nfe.classes.evento.manifestacaodestinatario;

import java.math.BigDecimal;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.nfe.validadores.BigDecimalParser;

public class NFEventoManifestacaoDestinatario extends DFBase {

    @Attribute(name = "versao", required = true)
    private String versao;

    @Element(name = "infEvento", required = true)
    private NFInfoEventoManifestacaoDestinatario infoEvento;

    @Element(name = "Signature", required = false)
    private String assinatura;

    public void setVersao(final BigDecimal versao) {
        this.versao = BigDecimalParser.tamanho5Com2CasasDecimais(versao, "Versao");
    }

    public NFInfoEventoManifestacaoDestinatario getInfoEvento() {
        return this.infoEvento;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setInfoEvento(final NFInfoEventoManifestacaoDestinatario infoEvento) {
        this.infoEvento = infoEvento;
    }

    public void setAssinatura(final String assinatura) {
        this.assinatura = assinatura;
    }

    public String getAssinatura() {
        return this.assinatura;
    }
}