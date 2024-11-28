package edu.miu.cse.finalproject.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
