import Models.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTests {

    @Test
    public void isActiveFalseTest(){
        Cell cell = new Cell();
        assertFalse(cell.getIsActive());
    }

    @Test
    public void isActiveTrueTest(){
        Cell cell = new Cell();
        cell.setIsActive(true);
        assertTrue(cell.getIsActive());
    }
}