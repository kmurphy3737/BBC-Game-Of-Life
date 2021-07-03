import Controllers.GridController;

import Helpers.DataReadIn;
import Models.Grid;


import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GridControllerTests {

    @Test
    public void generationNumberTest(){
        DataReadIn data = new DataReadIn();
        Grid grid = new Grid();
        GridController controller = new GridController(data, grid);

        int expectedGenNum = 2;
        controller.setGenerationNumber(expectedGenNum);
        int actualGenNum = controller.getGenerationNumber();
        assertEquals(expectedGenNum, actualGenNum);
    }

    @Test
    public void settingGridDataTest(){
        DataReadIn data = new DataReadIn();
        Grid grid = new Grid();
        GridController controller = new GridController(data, grid);

        List<List<Integer>> testList = new ArrayList<>();
        int size = 5;

        for(int i = 0; i<size; i++){
            testList.add(new ArrayList<>());
            for(int j = 0; j < size; j++){
                testList.get(i).add(0);
            }
        }

        controller.setGridData(testList);
        List<List<Integer>> resultList = controller.getGridData();

        for(int i = 0; i<size; i++){
            for(int j = 0; j < size; j++){
                assertEquals(testList.get(i).get(j), resultList.get(i).get(j));
            }
        }
    }
}