package com.fincatto.documentofiscal.cte200.classes.cte;

import org.joda.time.LocalDate;
import org.simpleframework.xml.Element;

import com.fincatto.documentofiscal.DFBase;

public class CTInfoAnulacao extends DFBase {

    @Element(name = "chCTe")
    private String chaveAcessoAnulado;

    @Element(name = "dEmi")
    private LocalDate dataEmissaoDeclaracao;

    public String getChaveAcessoAnulado() {
        return this.chaveAcessoAnulado;
    }

    public void setChaveAcessoAnulado(final String chaveAcessoAnulado) {
        this.chaveAcessoAnulado = chaveAcessoAnulado;
    }

    public LocalDate getDataEmissaoDeclaracao() {
        return this.dataEmissaoDeclaracao;
    }

    public void setDataEmissaoDeclaracao(final LocalDate dataEmissaoDeclaracao) {
        this.dataEmissaoDeclaracao = dataEmissaoDeclaracao;
    }

}
