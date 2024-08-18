package com.Library.libraryManagement.service;
import com.Library.libraryManagement.entitie.User;
import com.Library.libraryManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    public User getUser(String email)
    {
        Optional<User> oUser = userRepository.findByEmail(email);

        if(oUser.isEmpty())throw new RuntimeException("User does not exist");

       return oUser.get();
    }

}
