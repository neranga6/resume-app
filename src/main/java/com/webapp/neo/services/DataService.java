package com.webapp.neo.services;


import com.webapp.neo.model.PasswordForm;
import com.webapp.neo.repositories.PasswordFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DataService {

    @Autowired
    PasswordFormRepository passwordFormRepository ;
    public void savePassword(PasswordForm passwordForm) {
        passwordFormRepository.save(passwordForm);
    }


}
