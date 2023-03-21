package firstImplementation;

import org.isep.firstImplementation.AuthProgram;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AuthProgramTest {
    @Test
    public void launch() {
        AuthProgram authProgram1 = new AuthProgram("OAuth");
        assertEquals(authProgram1.authenticate("test","test"),"OAuthAuthentication");
        AuthProgram authProgram2 = new AuthProgram("Basic");
        assertEquals(authProgram2.authenticate("test","test"),"BasicAuthentication");
        AuthProgram authProgram3 = new AuthProgram("test");
        assertEquals(authProgram3.authenticate("test","test"),"Error");
        AuthProgram authProgram4 = new AuthProgram("Digest");
        assertEquals(authProgram4.authenticate("test","test"),"DigestAuthentication");
    }
}