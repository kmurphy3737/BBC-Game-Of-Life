package Controllers;

import Helpers.DataReadIn;
import Models.Grid;
import Views.GridView;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;


/**
 * Controller for Grid View.
 * Class is responsible for instructing the view what to display. It is also responsible for computations/manipulation
 * of the data read in and also the storage of that data within the Grid Model.
 */
public class GridController {

    //Constants
    /**
     * Number of miliseconds delay for timer.
     */
    private final int TIMER_DELAY = 500;

    //Instance Variables
    private DataReadIn data;
    private GridView gridView;
    private List<List<Integer>> gridData;
    private Grid grid;
    private Timer timer = null;
    private int generationNumber;

    /**
     * Constructor for Grid Controller with with parameters: a DataReadIn and Grid class to be provided.
     * This will ensure the controller is passed the correct DataReadIn and Grid class which the main method generated.
     * Will also set the gridData to the same grid data that DataReadIn created.
     * @param dataIn
     * @param grid
     */
    public GridController(DataReadIn dataIn, Grid grid){
        data = dataIn;

        if(data.getIsObtained()){
            gridData = data.getGridData();
            this.grid = grid;
        }
    }

    /**
     * Use this to begin the Game of Life.
     * It will initialise the grid view and start the JFrame.
     * It will also instruct the Grid instance to generate its cell grid.
     */
    public void start(){
        setupView();
        grid.setCellGrid(gridData);
        generationNumber = grid.getGenerationNumber();
    }

    /**
     * Getter for generation number.
     * Generation number will be set elsewhere where it will be obtained from the Grid instance.
     * @return int which contains the number of generations which have occurred since game began.
     */
    public int getGenerationNumber(){
        return generationNumber;
    }

    /**
     *  Set generation number to the parameter passed in.
     * @param genNum int passed in
     */
    public void setGenerationNumber(int genNum){
        generationNumber = genNum;
    }

    /**
     * Getter for grid data.
     * Data in here will be contained in 0s and 1s. 0 means the 'cell' is dead while 1 means the 'cell' is alive.
     * @return list of lists of integers which contain the data of the grid.
     */
    public List<List<Integer>> getGridData(){
        return gridData;
    }

    /**
     * Set gridData to the parameter passed in.
     * @param data - list of all grid data in 0s and 1s.
     */
    public void setGridData(List<List<Integer>> data){
        gridData = data;
    }

    /**
     * Initialise the GridView for this controller.
     * Will also pass the view the instance of this controller.
     */
    private void setupView(){
        gridView = new GridView(this);
    }

    /**
     * Refreshes the grid for every generation/iteration.
     * Starts a timer which will run every x number of miliseconds. Will run cell grid updates everytime run and will
     * update this back to the view and also update the generation number.
     */
    private void runIterations(){
        timer = new Timer(TIMER_DELAY, e -> {
            grid.updateCellGrid();
            gridData.clear();

            for(int i = 0; i < grid.getCellGrid().size(); i++){
                gridData.add(new ArrayList<>());
                for(int j = 0; j < grid.getCellGrid().get(0).size(); j++){
                    if(grid.getCellGrid().get(i).get(j).getIsActive()){
                        gridData.get(i).add(1);
                    }
                    else{
                        gridData.get(i).add(0);
                    }
                }
            }
            generationNumber = grid.getGenerationNumber();
            gridView.refreshGrid();
        });
        timer.start();
    }

    /**
     * Triggers beginning of the game once user has clicked the start button.
     */
    public void startGame(){
        generationNumber = grid.getGenerationNumber();
        gridView.refreshGrid();
        runIterations();
    }

    /**
     * Triggers pause of the game when user clicks pause button.
     * Will remain in this manner until user clicks either the resume or stop buttons.
     */
    public void pauseGame(){
        timer.stop();
        gridView.pauseGame();
    }

    /**
     * Stops the game when the user clicks the stop button.
     * Returns the user to the original seed data on the grid they had upon loading the program. Will return the grid
     * data to its original state and return the number of generations run to 0.
     */
    public void stopGame(){
        timer.stop();
        gridData.clear();
        data.setGridData();
        gridData = data.getGridData();
        grid.setCellGrid(gridData);
        grid.resetGenerationNumber();
        generationNumber = grid.getGenerationNumber();
        gridView.stopGame();
    }
}