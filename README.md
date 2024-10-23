# Conceitos Aprendidos no Exercício

## 1. Programação Orientada a Objetos (POO)
Classes e Objetos: Definição de classes (Banco, Cliente, Conta, ContaCorrente, ContaPoupanca) e criação de objetos a partir dessas classes.
Encapsulamento: Uso de modificadores de acesso (private, public) para proteger os dados e métodos das classes.
Herança: ContaCorrente e ContaPoupanca herdam de Conta.
Polimorfismo: Uso de métodos sobrecarregados e sobrescritos para diferentes tipos de contas.

## 2. Estruturas de Controle
Laços de Repetição: Uso de while para criar loops que permitem a interação contínua do usuário com o sistema.
Condicionais: Uso de if-else e switch-case para tomar decisões com base na entrada do usuário.

## 3. Coleções
Listas: Uso de ArrayList para armazenar clientes e contas.
Streams: Uso de streams para filtrar e encontrar elementos em coleções.

## 4. Entrada e Saída (I/O)
Scanner: Uso da classe Scanner para ler a entrada do usuário a partir do console.
System.out.println: Uso para exibir mensagens no console.

## 5. Tratamento de Erros
Validação de Entrada: Verificação de entradas inválidas e solicitação de nova entrada ao usuário.

## 6. Design de Interface de Usuário (UI)
Menus: Criação de menus interativos para guiar o usuário através das opções disponíveis no sistema.

## 7. Boas Práticas de Programação
Constantes: Uso de constantes (final static) para valores fixos, melhorando a legibilidade e manutenção do código.
Métodos Privados: Encapsulamento de lógica em métodos privados para modularizar e organizar o código.

### Diagrama em Mermaid

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
