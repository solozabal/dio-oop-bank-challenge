package com.solozabal.oopbank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
    }

    @Test
    public void testGetNome() {
        cliente.setNome("John Doe");
        assertEquals("John Doe", cliente.getNome());
    }

    @Test
    public void testSetNome() {
        cliente.setNome("John Doe");
        assertEquals("John Doe", cliente.getNome());
    }

    @Test
    public void testGetEmail() {
        cliente.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", cliente.getEmail());
    }

    @Test
    public void testSetEmail() {
        cliente.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", cliente.getEmail());
    }

    @Test
    public void testGetSenha() {
        cliente.setSenha("password123");
        assertEquals("password123", cliente.getSenha());
    }

    @Test
    public void testSetSenha() {
        cliente.setSenha("password123");
        assertEquals("password123", cliente.getSenha());
    }
}