import Controllers.GridController;
import Helpers.DataReadIn;
import Models.Grid;

public class Program {
    /**
     * Starts Game of Life.
     * Reads in data from csv file which user has filled in.
     * Populates grid and displays to user in JFrame.
     * @param args list of string arguments from the user
     */
    public static void main(String[] args){
        DataReadIn data = new DataReadIn();
        Grid grid = new Grid();
        GridController gridController = new GridController(data, grid);
        if(data.getIsObtained()){
            gridController.start();
        }
    }
}