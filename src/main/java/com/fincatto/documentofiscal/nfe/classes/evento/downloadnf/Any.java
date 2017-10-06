package com.fincatto.documentofiscal.nfe.classes.evento.downloadnf;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.nfe.classes.NFProtocolo;
import com.fincatto.documentofiscal.nfe.classes.nota.NFNota;

@Root(name = "nfeProc")
@Namespace(reference = "http://www.portalfiscal.inf.br/nfe")
public class Any extends DFBase{
	
	@Attribute(name = "versao", required = true)
	private String versao;
	
	@Element(name = "NFe", required = false)
	private NFNota nota;
	
	@Element(name = "protNFe", required = false)
	private NFProtocolo protocolo;
	
	public Any() {
		this.versao = null;
		this.nota = null;
		this.protocolo = null;
	}
	
	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public NFNota getNota() {
		return nota;
	}

	public void setNota(NFNota nota) {
		this.nota = nota;
	}

	public NFProtocolo getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(NFProtocolo protocolo) {
		this.protocolo = protocolo;
	}
}
