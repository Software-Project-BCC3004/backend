package br.es.pews.back.form;

public enum Pontuacao {
    AN0(0),
    AN1(1),
    AN2(2),
    AN3(3),

    AC0(0),
    AC1(1),
    AC2(2),
    AC3(3),

    AR0(0),
    AR1(1),
    AR2(2),
    AR3(3),

    EmeseNAO(0),
    EmeseSIM(2),

    NebulisacaoNAO(0),
    NebulisacaoSIM(2),
    ;


    private final int score;

    Pontuacao(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
