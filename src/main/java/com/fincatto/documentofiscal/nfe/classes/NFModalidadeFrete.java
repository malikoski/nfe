package com.fincatto.documentofiscal.nfe.classes;

public enum NFModalidadeFrete {
    POR_CONTA_DO_EMITENTE("0", "Por conta do emitente"),
    POR_CONTA_DO_DESTINATARIO_REMETENTE("1", "Por conta do destinat\u00e1rio remetente"),
    POR_CONTA_DE_TERCEIROS("2", "Por conta de terceiros"),
    PROPRIO_POR_CONTA_DO_REMETENTE("3", "Transporte Pr\u00f3prio por conta do Remetente"),
    PROPRIO_POR_CONTA_DO_DESTINATARIO("4", "Transporte Pr\u00f3prio por conta do Destinat\u00e1rio"),
    SEM_FRETE("9", "Sem frete");

    private final String codigo;
    private final String descricao;

    NFModalidadeFrete(final String codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public static NFModalidadeFrete valueOfCodigo(final String codigo) {
        for (final NFModalidadeFrete modalidadeFrete : NFModalidadeFrete.values()) {
            if (modalidadeFrete.getCodigo().equals(codigo)) {
                return modalidadeFrete;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return codigo + " - " + descricao;
    }
}