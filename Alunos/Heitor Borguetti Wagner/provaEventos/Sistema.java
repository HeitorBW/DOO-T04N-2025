package application;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
	
    private List<Evento> eventos = new ArrayList<>();

    private List<Cliente> clientes = new ArrayList<>();

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarCliente(String nome) {
        for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(nome)) return c;
        }
        return null;
    }

    public void comprarIngresso(int indice, Cliente cliente, boolean vip) {
        if (indice < 0 || indice >= eventos.size()) {
            System.out.println("Evento inválido.");
            return;
        }
        Evento evento = eventos.get(indice);
        if (evento.verificarDisponibilidade()) {
            double valor = evento.calcularValorIngresso(vip);
            evento.adicionarParticipante(cliente);
            System.out.println("Ingresso comprado. Valor: R$" + valor);
        } else {
            System.out.println("Evento lotado.");
        }
    }

    public void utilizarIngresso(int indice, Cliente cliente) {
        if (indice < 0 || indice >= eventos.size()) {
            System.out.println("Evento inválido.");
            return;
        }
        Evento evento = eventos.get(indice);
        evento.usarIngresso(cliente);
    }
    
    public void cadastrarEvento(Evento evento) {
        eventos.add(evento);
    }

    public void listarEventos() {
        for (Evento e : eventos) {
            System.out.println(e.nome);
        }
    }

    public void comprarIngresso(Evento evento, Cliente cliente, boolean vip) {
        if (evento.verificarDisponibilidade()) {
            double valor = evento.calcularValorIngresso(vip);
            evento.adicionarParticipante(cliente);
            System.out.println("Ingresso comprado por " + cliente + ". Valor: R$" + valor);
        } else {
            System.out.println("Sem vagas disponíveis para o evento.");
        }
    }
}
