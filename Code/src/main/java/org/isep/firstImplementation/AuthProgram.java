package org.isep.firstImplementation;

public class AuthProgram {

    String authenticationStrategy;

    public AuthProgram(String authenticationStrategy) {
        this.authenticationStrategy = authenticationStrategy;
    }

    public String authenticate(String username, String password) {
        switch (authenticationStrategy) {
            case "OAuth" -> {
                OAuthAuthentication oAuthInvoked = new OAuthAuthentication();
                return oAuthInvoked.authenticate();
            }
            case "Basic" -> {
                BasicAuthentication basicInvoked = new BasicAuthentication();
                return basicInvoked.authenticate();
            }
            case "Digest" -> {
                DigestAuthentication digestInvoked = new DigestAuthentication();
                return digestInvoked.authenticate();
            }
            default -> {
                return "Error";
            }
        }
    }

}
