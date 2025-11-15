package com.payment.service.service;

import com.payment.service.model.User;
import com.payment.service.repository.UserRepository;
import com.payment.service.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testes unitários da classe UserServiceImpl
 * Aqui testamos SOMENTE a regra de negócio, sem tocar no banco, controller ou contexto Spring.
 */
class UserServiceImplTest {

    private UserRepository userRepository;   // Mock do repositório
    private UserServiceImpl userService;     // Classe sendo testada

    /**
     * Executa antes de cada teste.
     * Cria o mock do UserRepository e instancia o serviço injetando o mock.
     */
    @BeforeEach
    void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    /**
     * Testa se o usuário é criado corretamente chamando o repository.save().
     */
    @Test
    void shouldCreateUser() {
        User user = new User();
        user.setName("Lucas");

        // Quando salvar, retorna o próprio user simulando o repositório
        when(userRepository.save(user)).thenReturn(user);

        // Executa o método do service
        User result = userService.create(user);

        // Verifica se o retorno foi correto
        assertEquals("Lucas", result.getName());

        // Verifica se o repository.save() foi chamado exatamente 1 vez
        verify(userRepository, times(1)).save(user);
    }

    /**
     * Testa a busca por ID quando o usuário existe.
     */
    @Test
    void shouldFindUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("Lucas");

        // Mockando o retorno de findById()
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Executa
        User result = userService.findById(1L);

        // Verifica se encontrou corretamente
        assertEquals(1, result.getId());
    }

    /**
     * Testa a busca por ID quando o usuário NÃO existe.
     * Esperado: lançar exceção.
     */
    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Mock do repository retornando vazio
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Verifica se lança a exceção esperada
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.findById(1L);
        });

        // Garante que a mensagem contém "User not found"
        assertTrue(ex.getMessage().contains("User not found"));
    }
}
