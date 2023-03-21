package secondImplementation;

import org.isep.secondImplementation.AuthProgram;
import org.isep.secondImplementation.BasicAuthentication;
import org.isep.secondImplementation.OAuthAuthentication;
import org.isep.secondImplementation.OpenIDAuthentication;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AuthProgramTest {
    @Test
    public void launch() {
        AuthProgram authProgram1 = new AuthProgram(new OAuthAuthentication());
        assertEquals(authProgram1.authenticate("test","test"),"OAuthAuthentication");
        AuthProgram authProgram2 = new AuthProgram(new BasicAuthentication());
        assertEquals(authProgram2.authenticate("test","test"),"BasicAuthentication");
        AuthProgram authProgram3 = new AuthProgram(new OpenIDAuthentication());
        assertEquals(authProgram3.authenticate("test","test"),"OpenIDAuthentication");
    }
}