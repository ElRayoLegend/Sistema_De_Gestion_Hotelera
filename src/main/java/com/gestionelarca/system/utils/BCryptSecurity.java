package com.gestionelarca.system.utils;

import org.springframework.stereotype.Component;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Component
public class BCryptSecurity {
    public String encodePassword(String password){
        return BCrypt.withDefaults().hashToString(10, password.toCharArray());
    }

    public boolean checkPassword(String password, String hashedPassword){
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }
}
