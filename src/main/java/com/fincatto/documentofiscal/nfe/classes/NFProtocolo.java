package com.fincatto.documentofiscal.nfe.classes;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.DFBase;

@Root(name = "protNFe")
public class NFProtocolo extends DFBase {

    @Attribute(name = "versao", required = true)
    private String versao;

    @Element(name = "infProt", required = true)
    private NFProtocoloInfo protocoloInfo;

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public void setProtocoloInfo(final NFProtocoloInfo protocoloInfo) {
        this.protocoloInfo = protocoloInfo;
    }

    public NFProtocoloInfo getProtocoloInfo() {
        return this.protocoloInfo;
    }

    public String getVersao() {
        return this.versao;
    }
}