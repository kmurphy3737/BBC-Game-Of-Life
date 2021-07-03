import Helpers.DataReadIn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataReadInTests {

    @Test
    public void hasFoundFileTest(){
        DataReadIn data = new DataReadIn();

        assertTrue(data.getIsObtained());
    }
}
