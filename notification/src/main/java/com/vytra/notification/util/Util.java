package com.vytra.notification.util;

import com.vytra.notification.entity.security.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class Util {

    /**
     * Valida si usuario esta authenticado
     */
    public static User userAuth() {
        try {
            Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
            if (auth.isPresent()) {
                return (User) auth.get().getPrincipal();
            } else {
                return null;
            }
        }catch (ClassCastException e){
            //Session perdida o Inexistente
            return null;
        }
    }

}
