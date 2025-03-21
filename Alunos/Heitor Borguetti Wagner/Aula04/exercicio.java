package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Venda {
    int quantidade;
    double precoUnitario;
    double desconto;
    double valorTotal;

    public Venda(int quantidade, double precoUnitario, double desconto, double valorTotal) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Quantidade: " + quantidade + ", Preço Unitário: R$ " + precoUnitario + ", Desconto: R$ " + desconto + ", Valor Total: R$ " + valorTotal;
    }
}

class RegistroDeVendas {
    // Mapeia o mês (1 a 12) para as vendas feitas naquele mês.
    private List<List<Venda>> vendasPorMes;

    public RegistroDeVendas() {
        vendasPorMes = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            vendasPorMes.add(new ArrayList<>());
        }
    }

    // Adiciona uma venda em um mês específico
    public void adicionarVenda(int mes, Venda venda) {
        vendasPorMes.get(mes - 1).add(venda); // Meses são indexados de 0 a 11
    }

    // Recupera as vendas de um determinado mês
    public List<Venda> obterVendasPorMes(int mes) {
        return vendasPorMes.get(mes - 1);
    }

    // Recupera as vendas de um mês e dia específicos
    public List<Venda> obterVendasPorDia(int mes, int dia) {
        return vendasPorMes.get(mes - 1); // Para simplificação, estamos considerando as vendas por dia na mesma lista do mês
    }

    // Soma total de vendas por mês
    public double obterTotalPorMes(int mes) {
        double total = 0;
        for (Venda venda : vendasPorMes.get(mes - 1)) {
            total += venda.valorTotal;
        }
        return total;
    }
}

public class Program {
    private static RegistroDeVendas registroDeVendas = new RegistroDeVendas();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Calculadora da Loja de Plantas ===");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Registro de Vendas");
            System.out.println("[4] - Buscar Vendas por Mês");
            System.out.println("[5] - Buscar Vendas por Mês e Dia");
            System.out.println("[6] - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;
                case 2:
                    calcularTroco(scanner);
                    break;
                case 3:
                    exibirRegistroVendas();
                    break;
                case 4:
                    buscarVendasPorMes(scanner);
                    break;
                case 5:
                    buscarVendasPorDia(scanner);
                    break;
                case 6:
                    System.out.println("Saindo... Obrigado por usar a calculadora!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }

    public static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        double precoTotal = quantidade * precoUnitario;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = precoTotal * 0.05;
            precoTotal -= desconto;
        }

        System.out.printf("Preço total da compra: R$ %.2f (Desconto aplicado: R$ %.2f)\n", precoTotal, desconto);

        // Armazena a venda no registro (considerando o mês atual como mês 3 por exemplo)
        registroDeVendas.adicionarVenda(3, new Venda(quantidade, precoUnitario, desconto, precoTotal));
    }

    public static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor recebido do cliente: ");
        double valorRecebido = scanner.nextDouble();
        System.out.print("Digite o valor total da compra: ");
        double valorCompra = scanner.nextDouble();

        if (valorRecebido < valorCompra) {
            System.out.println("Valor recebido é insuficiente!");
        } else {
            double troco = valorRecebido - valorCompra;
            System.out.printf("Troco a ser dado: R$ %.2f\n", troco);
        }
    }

    public static void exibirRegistroVendas() {
        System.out.println("\n=== Registro de Vendas ===");
        for (int i = 0; i < 12; i++) {
            System.out.println("Mês " + (i + 1) + ":");
            List<Venda> vendas = registroDeVendas.obterVendasPorMes(i + 1);
            if (vendas.isEmpty()) {
                System.out.println("Nenhuma venda registrada.");
            } else {
                for (Venda venda : vendas) {
                    System.out.println(venda);
                }
            }
        }
    }

    public static void buscarVendasPorMes(Scanner scanner) {
        System.out.print("Digite o número do mês (1 a 12): ");
        int mes = scanner.nextInt();
        double total = registroDeVendas.obterTotalPorMes(mes);
        System.out.printf("Total de vendas no mês %d: R$ %.2f\n", mes, total);
    }

    public static void buscarVendasPorDia(Scanner scanner) {
        System.out.print("Digite o número do mês (1 a 12): ");
        int mes = scanner.nextInt();
        System.out.print("Digite o número do dia (1 a 31): ");
        int dia = scanner.nextInt();
        List<Venda> vendas = registroDeVendas.obterVendasPorDia(mes, dia);

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada para esse dia.");
        } else {
            System.out.println("Vendas do dia " + dia + " do mês " + mes + ":");
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }
}
