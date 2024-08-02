package com.solozabal.oopbank;

/**
 * Interface representing a bank account.
 */
public interface IConta {
    /**
     * Withdraws a specified amount from the account.
     * @param valor the amount to withdraw
     */
    void sacar(double valor);

    /**
     * Deposits a specified amount into the account.
     * @param valor the amount to deposit
     */
    void depositar(double valor);

    /**
     * Transfers a specified amount to another account.
     * @param valor the amount to transfer
     * @param contaDestino the destination account
     */
    void transferir(double valor, IConta contaDestino);

    /**
     * Prints the account statement.
     */
    void imprimirExtrato();
}