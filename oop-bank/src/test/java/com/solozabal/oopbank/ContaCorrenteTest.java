package com.solozabal.oopbank;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaCorrenteTest {
    private ContaCorrente contaCorrente;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setNome("John Doe");
        contaCorrente = new ContaCorrente(cliente);
        contaCorrente.setAgencia(1234);
        contaCorrente.setNumero(5678);
        contaCorrente.setSaldo(1000.00);
    }

    @Test
    public void testImprimirExtrato() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        contaCorrente.imprimirExtrato();

        String expectedOutput = """
                === Extrato Conta Corrente ===
                Titular: John Doe
                Agência: 1234
                Número: 5678
                Saldo: 1000.00
                """;

        String actualOutput = outContent.toString().replace("\r\n", "\n").trim();
        expectedOutput = expectedOutput.replace("\r\n", "\n").trim();

        assertEquals(expectedOutput, actualOutput);

        System.setOut(null);
    }
}