package com.aftech.wppclone.interceptor;

import com.aftech.wppclone.user.UserSyncronizer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserSyncronizerFilter extends OncePerRequestFilter {

    private final UserSyncronizer userSyncronizer;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        if(!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.
                    getContext().getAuthentication();

            userSyncronizer.syncronizeWithIdp(token.getToken());
        }
        filterChain.doFilter(request, response);
    }
}
