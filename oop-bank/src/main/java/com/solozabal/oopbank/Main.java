package com.solozabal.oopbank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Banco banco = new Banco();
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        banco.setNome("Banco Digital");
        banco.setContas(new ArrayList<>());

        System.out.println("Bem-vindo ao " + banco.getNome());

        while (true) {
            System.out.println("\n1. Cadastrar Cliente");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return; // Encerra o loop e termina a aplicação
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private static void cadastrarCliente() {
        Cliente cliente = new Cliente();

        System.out.print("Nome: ");
        cliente.setNome(scanner.nextLine());

        System.out.print("Email: ");
        cliente.setEmail(scanner.nextLine());

        System.out.print("Senha: ");
        cliente.setSenha(scanner.nextLine());

        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void login() {
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Cliente cliente = clientes.stream()
                .filter(c -> c.getEmail().equals(email) && c.getSenha().equals(senha))
                .findFirst()
                .orElse(null);

        if (cliente != null) {
            System.out.println("Login realizado com sucesso!");
            operacoes(cliente);
        } else {
            System.out.println("Email ou senha incorretos!");
        }
    }

    private static void operacoes(Cliente cliente) {
        System.out.println("Deseja abrir qual tipo de conta?");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        int tipoConta = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        Conta conta;
        if (tipoConta == 1) {
            conta = new ContaCorrente(cliente);
        } else if (tipoConta == 2) {
            conta = new ContaPoupanca(cliente);
        } else {
            System.out.println("Tipo de conta inválido!");
            return;
        }

        banco.getContas().add(conta);

        // Solicitar o depósito inicial de R$100,00
        System.out.println("Depósito inicial obrigatório de R$100,00");
        System.out.print("Digite o valor do depósito inicial: ");
        double depositoInicial = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer

        if (depositoInicial == 100.00) {
            conta.depositar(depositoInicial);
            System.out.println("Depósito inicial realizado com sucesso!");
        } else {
            System.out.println("O valor do depósito inicial deve ser exatamente R$100,00");
            banco.getContas().remove(conta); // Remove a conta se o depósito não for de 100 reais
            return; // Retorna para o menu de login sem mostrar as opções de operação
        }

        while (true) {
            System.out.println("\n1. Fazer depósito");
            System.out.println("2. Consultar saldo");
            System.out.println("3. Transferir valores entre contas");
            System.out.println("4. Sacar");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    depositar(conta);
                    break;
                case 2:
                    conta.imprimirExtrato();
                    break;
                case 3:
                    transferir(conta);
                    break;
                case 4:
                    sacar(conta);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return; // Encerra o loop das operações e retorna ao menu principal
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private static void depositar(Conta conta) {
        System.out.print("Valor do depósito: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer
        conta.depositar(valor);
        System.out.println("Depósito realizado com sucesso!");
    }

    private static void sacar(Conta conta) {
        System.out.print("Valor do saque: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer
        conta.sacar(valor);
        System.out.println("Saque realizado com sucesso!");
    }

    private static void transferir(Conta contaOrigem) {
        System.out.print("Valor da transferência: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer

        Conta contaDestino = banco.getContas().stream()
                .filter(conta -> conta.getCliente().equals(contaOrigem.getCliente()) &&
                        conta.getClass() != contaOrigem.getClass())
                .findFirst()
                .orElse(null);

        if (contaDestino != null) {
            contaOrigem.transferir(valor, contaDestino);
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Conta destino não encontrada!");
        }
    }
}
