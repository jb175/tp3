package org.isep.secondImplementation;

public class AuthProgram {

    private IAuthenticationStrategy authenticationStrategy;

    public AuthProgram(IAuthenticationStrategy authenticationStrategy) {
        this.authenticationStrategy = authenticationStrategy;
    }

    public String authenticate(String username, String password) {
        return authenticationStrategy.authenticate();
    }

}
