package com.mubification.services;

import com.mubification.models.User;
import com.mubification.repositories.UserRepository;


public class UserService {
 
    private UserRepository userRepository;

    public UserService() {
        this. userRepository = new UserRepository();
    }

    // adiciona um novo usuário
    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    // verifica se já existe um usuário com o mesmo email/username no banco
    public Boolean userExists(User user) {
        return userRepository.userExists(user);
    }

    // autenticação
    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    // pegar informações do usuario
    public User getUserInfo(int userId) {
        return userRepository.getUserInfo(userId);
    }


}
