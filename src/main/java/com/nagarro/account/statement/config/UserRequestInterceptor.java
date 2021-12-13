package com.nagarro.account.statement.config;

import com.nagarro.account.statement.exception.UnauthorizedOperationException;
import com.nagarro.account.statement.security.SystemUser;
import com.nagarro.account.statement.security.UserPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Component
@Slf4j
public class UserRequestInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("UserRequestInterceptor - preHandler");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof SystemUser) {
            Collection<? extends GrantedAuthority> authorities = ((SystemUser) principal).getAuthorities();

            boolean isRestricted = authorities.stream()
                    .noneMatch(
                            grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase(UserPermission.NON_RESTRICTED_READ.getPermission()
                            )
                    );

            if (request.getParameterMap().size() > 1 && isRestricted) {
                throw new UnauthorizedOperationException("not allowed to perform this operation - only parameter allowed is account id");
            }
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}