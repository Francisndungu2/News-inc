import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    public void creates_instanceOfApp_true(){
        App newApp = new App();
        assertTrue(newApp instanceof App);
    }


}