package application;

import java.util.ArrayList;
import java.util.List;

public abstract class Evento {
    protected String nome;
    protected int duracaoDias;
    protected double precoDiario;
    protected int maxParticipantes;
    protected List<Cliente> participantes = new ArrayList<>();

    public Evento(String nome, int duracaoDias, double precoDiario, int maxParticipantes) {
        this.nome = nome;
        this.duracaoDias = duracaoDias;
        this.precoDiario = precoDiario;
        this.maxParticipantes = maxParticipantes;
    }

    public boolean verificarDisponibilidade() {
        return participantes.size() < maxParticipantes;
    }

    public void adicionarParticipante(Cliente cliente) {
        if (verificarDisponibilidade()) {
            participantes.add(cliente);
        } else {
            System.out.println("Evento lotado.");
        }
    }

    public double calcularValorIngresso(boolean vip) {
        return precoDiario * duracaoDias;
    }

    public abstract void usarIngresso(Cliente cliente);
}