package io.lozzikit.sdk.security;

import io.jsonwebtoken.Jwts;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.lozzikit.sdk.annotation.Authentication;
import io.lozzikit.sdk.security.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * This class filters valid JWT
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();

        if (method.isAnnotationPresent(Authentication.class)) {

            // Get token from request
            String token = request.getHeader(SecurityConstants.HEADER_STRING);

            if (null == token) {
                response.setStatus(401);
                return false;
            }

            try {
                // parse the token.
                String user = Jwts.parser()
                        .setSigningKey(SecurityConstants.SECRET)
                        .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();

                if(user != null) {
                    request.setAttribute("user", user);
                }
                else {
                    response.setStatus(401);
                    return false;
                }
            }
            catch(Exception e) {

                //  Return 401 for any exceptions (This include ExpiredJwtException, MalformedJwtException, ...)
                response.setStatus(401);
                return false;
            }
        }

        return super.preHandle(request, response, handler);
    }
}
