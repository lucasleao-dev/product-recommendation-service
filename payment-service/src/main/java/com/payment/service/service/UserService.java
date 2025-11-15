package com.payment.service.service;

import com.payment.service.model.User;
import java.util.List;

public interface UserService {

    User create(User user);

    User findById(Long id);

    List<User> findAll();
}
