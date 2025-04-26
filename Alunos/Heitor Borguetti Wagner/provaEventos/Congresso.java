package application;

public class Congresso extends Evento {
    public Congresso(String nome, int duracaoDias, double precoDiario, int maxParticipantes) {
        super(nome, duracaoDias, precoDiario, maxParticipantes);
    }

    @Override
    public void usarIngresso(Cliente cliente) {
        System.out.println(cliente + " usou ingresso no congresso " + nome);
    }
}