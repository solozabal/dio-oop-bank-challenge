# Digital Bank Application

This is a simple digital bank application implemented in Java. It allows users to register, log in, create accounts, and perform basic banking operations such as deposit, withdrawal, and transfer between accounts.

## Project Structure

The project includes the following classes:

- **Banco**: Manages the list of accounts and provides methods to access and modify the bank's name and accounts.
- **Cliente**: Represents a client of the bank with attributes for name, email, and password.
- **Conta**: Abstract base class for bank accounts with methods for deposit, withdrawal, transfer, and printing the account statement.
- **ContaCorrente**: A subclass of `Conta` representing a checking account.
- **ContaPoupanca**: A subclass of `Conta` representing a savings account.
- **IConta**: Interface that defines methods required for all account types.

## Class Diagram

Below is the class diagram for the digital bank application:

```mermaid
classDiagram
    class Banco {
        -String nome
        -List<Conta> contas
        +getNome(): String
        +setNome(nome: String): void
        +getContas(): List<Conta>
        +setContas(contas: List<Conta>): void
    }

    class Cliente {
        -String nome
        -String email
        -String senha
        +getNome(): String
        +setNome(nome: String): void
        +getEmail(): String
        +setEmail(email: String): void
        +getSenha(): String
        +setSenha(senha: String): void
    }

    class IConta {
        <<interface>>
        +sacar(valor: double): void
        +depositar(valor: double): void
        +transferir(valor: double, contaDestino: IConta): void
        +imprimirExtrato(): void
    }

    class Conta {
        <<abstract>>
        -int agencia
        -int numero
        -double saldo
        -Cliente cliente
        +Conta(cliente: Cliente)
        +sacar(double valor): void
        +depositar(double valor): void
        +transferir(double valor, IConta contaDestino): void
        +getAgencia(): int
        +getNumero(): int
        +getSaldo(): double
        +getCliente(): Cliente
        +imprimirExtrato(): void
    }

    class ContaCorrente {
        +ContaCorrente(cliente: Cliente)
        +imprimirExtrato(): void
    }

    class ContaPoupanca {
        +ContaPoupanca(cliente: Cliente)
        +imprimirExtrato(): void
    }

    Banco "1" --> "many" Conta
    Conta "1" --> "1" Cliente
    ContaCorrente --|> Conta
    ContaPoupanca --|> Conta
    Conta ..|> IConta
