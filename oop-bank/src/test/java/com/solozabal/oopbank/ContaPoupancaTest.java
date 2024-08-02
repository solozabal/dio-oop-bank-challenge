package com.solozabal.oopbank;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaPoupancaTest {
    private ContaPoupanca contaPoupanca;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setNome("Jane Doe");
        contaPoupanca = new ContaPoupanca(cliente);
        contaPoupanca.setAgencia(1234);
        contaPoupanca.setNumero(5678);
        contaPoupanca.setSaldo(2000.00);
    }

    @Test
    public void testImprimirExtrato() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Set the default locale to US to ensure the decimal separator is a period
        Locale.setDefault(Locale.US);

        contaPoupanca.imprimirExtrato();

        String expectedOutput = """
                === Extrato Conta Poupança ===
                Titular: Jane Doe
                Agência: 1234
                Número: 5678
                Saldo: 2000.00
                """;

        String actualOutput = outContent.toString().replace("\r\n", "\n").trim();
        expectedOutput = expectedOutput.replace("\r\n", "\n").trim();

        assertEquals(expectedOutput, actualOutput);

        System.setOut(null);
    }
}