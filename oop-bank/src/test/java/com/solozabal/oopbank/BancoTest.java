package com.solozabal.oopbank;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BancoTest {
    private Banco banco;

    @BeforeEach
    public void setUp() {
        banco = new Banco();
    }

    @Test
    public void testGetNome() {
        banco.setNome("Banco Teste");
        assertEquals("Banco Teste", banco.getNome());
    }

    @Test
    public void testSetNome() {
        banco.setNome("Banco Teste");
        assertEquals("Banco Teste", banco.getNome());
    }

    @Test
    public void testGetContas() {
        List<Conta> contas = new ArrayList<>();
        banco.setContas(contas);
        assertEquals(contas, banco.getContas());
    }

    @Test
    public void testSetContas() {
        List<Conta> contas = new ArrayList<>();
        banco.setContas(contas);
        assertEquals(contas, banco.getContas());
    }

    @Test
    public void testBancoConstructor() {
        assertNotNull(banco.getContas());
        assertTrue(banco.getContas().isEmpty());
    }
}