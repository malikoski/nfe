package com.fincatto.documentofiscal.cte200.classes.cte;

import org.joda.time.LocalTime;
import org.simpleframework.xml.Element;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.cte200.classes.CTTipoEntregaHorario;

public class CTInfoEntregaComHora extends DFBase {

    @Element(name = "tpHor")
    private CTTipoEntregaHorario tipoHorario;

    @Element(name = "hProg")
    private LocalTime horarioProgramado;

    public LocalTime getHorarioProgramado() {
        return this.horarioProgramado;
    }

    public void setHorarioProgramado(final LocalTime horarioProgramado) {
        this.horarioProgramado = horarioProgramado;
    }

    public CTTipoEntregaHorario getTipoHorario() {
        return this.tipoHorario;
    }

    public void setTipoHorario(final CTTipoEntregaHorario tipoHorario) {
        if (!CTTipoEntregaHorario.COM_HORARIO.contains(tipoHorario)) {
            throw new IllegalArgumentException("O tipo de per\u00edodo programado para entrega deve ser com horario");
        }
        this.tipoHorario = tipoHorario;
    }
}
