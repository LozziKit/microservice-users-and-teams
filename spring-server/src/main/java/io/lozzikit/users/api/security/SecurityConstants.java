package io.lozzikit.users.api.security;

public class SecurityConstants {
    public static final String SECRET = "PasImprimanteLouperSYM";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTH_USER_URL = "/auth";
    public static final String CREATE_USER_URL = "/users";
}
