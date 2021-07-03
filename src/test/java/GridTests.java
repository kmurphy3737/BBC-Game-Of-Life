import Models.Cell;
import Models.Grid;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GridTests {

    @Test
    public void generationNumberChangeTest(){
        Grid grid = new Grid();
        int expectedGenNum = 5;
        int actualGenNum;

        grid.setGenerationNumber(expectedGenNum);
        actualGenNum = grid.getGenerationNumber();

        assertEquals(expectedGenNum, actualGenNum);
    }

    @Test
    public void resetGenerationNumberToZeroTest(){
        Grid grid = new Grid();

        grid.setGenerationNumber(5);
        grid.resetGenerationNumber();
        int actualGenNum = grid.getGenerationNumber();

        assertEquals(0, actualGenNum);
    }

    @Test
    public void setCellGridTest(){
        Grid grid = new Grid();

        List<List<Integer>> testList = new ArrayList<>();
        int size = 5;

        for(int i = 0; i<size; i++){
            testList.add(new ArrayList<>());
            for(int j = 0; j < size; j++){
                testList.get(i).add(0);
            }
        }

        grid.setCellGrid(testList);
        List<List<Cell>> resultList = grid.getCellGrid();

        for(int i = 0; i<size; i++){
            for(int j = 0; j < size; j++){
                assertFalse(resultList.get(i).get(j).getIsActive());
            }
        }
    }

    @Test
    public void updateCellGridTest(){
        Grid grid = new Grid();

        List<List<Integer>> testList = new ArrayList<>();
        int size = 5;

        for(int i = 0; i<size; i++){
            testList.add(new ArrayList<>());
            for(int j = 0; j < size; j++){
                if(i == 2 && (j == 1 || j == 2 || j == 3)){
                    testList.get(i).add(1);
                }
                else{
                    testList.get(i).add(0);
                }
            }
        }

        grid.setCellGrid(testList);
        grid.updateCellGrid();
        List<List<Cell>> resultList = grid.getCellGrid();

        for(int i = 0; i<size; i++){
            for(int j = 0; j < size; j++){
                if((i == 1 || i == 2 || i == 3) && j == 2){
                    assertTrue(resultList.get(i).get(j).getIsActive());
                }
                else{
                    assertFalse(resultList.get(i).get(j).getIsActive());
                }
            }
        }
    }
}
