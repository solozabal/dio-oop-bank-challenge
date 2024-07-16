# Digital Bank Application

This is a simple digital bank application implemented in Java. It allows users to register, log in, create accounts, and perform basic banking operations such as deposit, withdrawal, and transfer between accounts.

## Project Structure

The project includes the following classes:

- **Main**: Entry point of the application that handles the main menu and user interactions.
- **Banco**: Manages the list of accounts and provides methods to access and modify the bank's name and accounts.
- **Cliente**: Represents a client of the bank with attributes for name, email, and password.
- **Conta**: Base class for bank accounts with methods for deposit, withdrawal, transfer, and printing the account statement.
- **ContaCorrente**: A subclass of `Conta` representing a checking account with an additional limit attribute.
- **ContaPoupanca**: A subclass of `Conta` representing a savings account with an additional interest rate attribute.
- **IConta**: Interface that defines methods required for all account types.

## Class Diagram

Below is the class diagram for the digital bank application:

```mermaid
classDiagram
    class Main {
        +void main(String[] args)
        -void cadastrarCliente()
        -void login()
        -void operacoes(Cliente cliente)
        -void depositar(Conta conta)
        -void sacar(Conta conta)
        -void transferir(Conta contaOrigem)
    }

    class Banco {
        -String nome
        -List<Conta> contas
        +String getNome()
        +void setNome(String nome)
        +List<Conta> getContas()
        +void setContas(List<Conta> contas)
    }

    class Cliente {
        -String nome
        -String email
        -String senha
        +String getNome()
        +void setNome(String nome)
        +String getEmail()
        +void setEmail(String email)
        +String getSenha()
        +void setSenha(String senha)
    }

    class Conta {
        -Cliente cliente
        -double saldo
        +Cliente getCliente()
        +void setCliente(Cliente cliente)
        +double getSaldo()
        +void depositar(double valor)
        +void sacar(double valor)
        +void transferir(double valor, Conta contaDestino)
        +void imprimirExtrato()
    }

    class ContaCorrente {
        +double limite
    }

    class ContaPoupanca {
        +double rendimento
    }

    class IConta {
        +void depositar(double valor)
        +void sacar(double valor)
        +void transferir(double valor, Conta contaDestino)
        +void imprimirExtrato()
    }

    Banco "1" -- "0..*" Conta : has
    Cliente "1" -- "0..*" Conta : owns
    ContaCorrente "0..1" --|> Conta : inherits
    ContaPoupanca "0..1" --|> Conta : inherits
    Conta "1" -- "1" IConta : implements
    Main -- Banco : uses
