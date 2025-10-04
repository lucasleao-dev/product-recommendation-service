package com.product.userservice.service;

import com.product.userservice.model.User;
import com.product.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user1 = new User("john", "pass123", "USER");
        user1.setId(1L);

        user2 = new User("jane", "pass456", "ADMIN");
        user2.setId(2L);
    }

    @Test
    void testFindAll() {
        List<User> users = Arrays.asList(user1, user2);
        when(repository.findAll()).thenReturn(users);

        List<User> result = service.findAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindByIdFound() {
        when(repository.findById(1L)).thenReturn(Optional.of(user1));

        Optional<User> result = service.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("john", result.get().getUsername());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<User> result = service.findById(99L);

        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void testSave() {
        when(repository.save(user1)).thenReturn(user1);

        User result = service.save(user1);

        assertEquals("john", result.getUsername());
        verify(repository, times(1)).save(user1);
    }

    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
