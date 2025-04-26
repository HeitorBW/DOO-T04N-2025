package application;

public class Show extends Evento {
    private final double vipExtra = 1.5;
    private int vagasVIP;

    public Show(String nome, int duracaoDias, double precoDiario, int maxParticipantes) {
        super(nome, duracaoDias, precoDiario, maxParticipantes);
        this.vagasVIP = (int) (maxParticipantes * 0.1);
    }

    @Override
    public double calcularValorIngresso(boolean vip) {
        if (vip && vagasVIP > 0) {
            return super.calcularValorIngresso(true) * vipExtra;
        }
        return super.calcularValorIngresso(false);
    }

    @Override
    public void usarIngresso(Cliente cliente) {
        System.out.println(cliente + " usou ingresso no show " + nome);
    }
}
