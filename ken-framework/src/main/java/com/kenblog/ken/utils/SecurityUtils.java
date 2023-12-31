package com.kenblog.ken.utils;

import com.kenblog.ken.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static LoginUser getLoginUser() {
        LoginUser principal = (LoginUser) getAuthentication().getPrincipal();
        if (principal!= null){
            return principal;
        }
        return null;
    }

    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin(){
        Long id = getLoginUser().getUser().getId();
        return id!=null && 1L == id;
    }

    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }

}
