package com.solozabal.oopbank;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Banco banco = new Banco();
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Cliente> clientes = new ArrayList<>();

    private static final int OPTION_REGISTER = 1;
    private static final int OPTION_LOGIN = 2;
    private static final int OPTION_EXIT = 3;

    private static final int ACCOUNT_CHECKING = 1;
    private static final int ACCOUNT_SAVINGS = 2;

    private static final int OPERATION_DEPOSIT = 1;
    private static final int OPERATION_BALANCE = 2;
    private static final int OPERATION_TRANSFER = 3;
    private static final int OPERATION_WITHDRAW = 4;
    private static final int OPERATION_EXIT = 5;

    public static void main(String[] args) {
        banco.setNome("Banco Digital");
        banco.setContas(new ArrayList<>());

        System.out.println("Bem-vindo ao " + banco.getNome());

        while (true) {
            displayMainMenu();
            int opcao = getUserInput();

            switch (opcao) {
                case OPTION_REGISTER -> cadastrarCliente();
                case OPTION_LOGIN -> login();
                case OPTION_EXIT -> {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n1. Cadastrar Cliente");
        System.out.println("2. Login");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int getUserInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.next(); // Clear invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        return input;
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
        int tipoConta = getUserInput();

        Conta conta;
        switch (tipoConta) {
            case ACCOUNT_CHECKING -> conta = new ContaCorrente(cliente);
            case ACCOUNT_SAVINGS -> conta = new ContaPoupanca(cliente);
            default -> {
                System.out.println("Tipo de conta inválido!");
                return;
            }
        }

        banco.getContas().add(conta);

        if (!validateInitialDeposit(conta)) {
            banco.getContas().remove(conta);
            return;
        }

        while (true) {
            displayOperationsMenu();
            int opcao = getUserInput();

            switch (opcao) {
                case OPERATION_DEPOSIT -> depositar(conta);
                case OPERATION_BALANCE -> conta.imprimirExtrato();
                case OPERATION_TRANSFER -> transferir(conta);
                case OPERATION_WITHDRAW -> sacar(conta);
                case OPERATION_EXIT -> {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void displayOperationsMenu() {
        System.out.println("\n1. Fazer depósito");
        System.out.println("2. Consultar saldo");
        System.out.println("3. Transferir valores entre contas");
        System.out.println("4. Sacar");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static boolean validateInitialDeposit(Conta conta) {
        System.out.println("Depósito inicial obrigatório de R$100,00");
        System.out.print("Digite o valor do depósito inicial: ");
        double depositoInicial = getUserInput();

        if (depositoInicial == 100.00) {
            conta.depositar(depositoInicial);
            System.out.println("Depósito inicial realizado com sucesso!");
            return true;
        } else {
            System.out.println("O valor do depósito inicial deve ser exatamente R$100,00");
            return false;
        }
    }

    private static void depositar(Conta conta) {
        System.out.print("Valor do depósito: ");
        double valor = getUserInput();
        conta.depositar(valor);
        System.out.println("Depósito realizado com sucesso!");
    }

    private static void sacar(Conta conta) {
        System.out.print("Valor do saque: ");
        double valor = getUserInput();
        conta.sacar(valor);
        System.out.println("Saque realizado com sucesso!");
    }

    private static void transferir(Conta contaOrigem) {
        System.out.print("Valor da transferência: ");
        double valor = getUserInput();

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