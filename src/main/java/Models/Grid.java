package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Grid class which tracks all Cells in the grid and the generation number.
 */
public class Grid {

    //Constants
    /**
     * String for North neighbour position.
     */
    private final String NORTH = "North";
    /**
     * String for North East neighbour position.
     */
    private final String NORTH_EAST = "North East";
    /**
     * String for East neighbour position.
     */
    private final String EAST = "East";
    /**
     * String for South East neighbour position.
     */
    private final String SOUTH_EAST = "South East";
    /**
     * String for South neighbour position.
     */
    private final String SOUTH = "South";
    /**
     * String for South West neighbour position.
     */
    private final String SOUTH_WEST = "South West";
    /**
     * String for West neighbour position.
     */
    private final String WEST = "West";
    /**
     * String for North West neighbour position.
     */
    private final String NORTH_WEST = "North West";

    //Instance Variables
    private List<List<Cell>> cellGrid;
    private int generationNumber;

    /**
     * Constructor for Grid with no parameters.
     * Initialises cellGrid and generationNumber.
     */
    public Grid(){
        cellGrid = new ArrayList<>();
        generationNumber = 0;
    }

    /**
     * Gets the generation number.
     * @return int: generation number - tracking how many generations have occurred.
     */
    public int getGenerationNumber(){
        return generationNumber;
    }

    /**
     * Sets the generation number to that of the parameter if parameter is positive int.
     * @param genNum is new int for which generation number currently on.
     */
    public void setGenerationNumber(int genNum){
        if(genNum >= 0){
            generationNumber = genNum;
        }
    }

    /**
     * Resets the generationNumber to 0.
     */
    public void resetGenerationNumber(){
        generationNumber = 0;
    }

    /**
     * Gets the list which contains all cells in the grid.
     * @return list<list<cell>>: cellGrid - list of all Cells in the grid.
     */
    public List<List<Cell>> getCellGrid(){
        return cellGrid;
    }

    /**
     * Sets the cellGrid using the gridData info provided in the parameter.
     * @param gridData list<list<integer>> contains data of grid in 0s and 1s
     */
    public void setCellGrid(List<List<Integer>> gridData){
        if(!cellGrid.isEmpty()){
            cellGrid.clear();
        }
        for(int i=0; i<gridData.size(); i++){
            cellGrid.add(new ArrayList<>());
            for(int j=0; j<gridData.get(0).size(); j++){
                Cell current = new Cell();
                int active = gridData.get(i).get(j);

                if(active == 0){
                    current.setIsActive(false);
                }
                else{
                    current.setIsActive(true);
                }
                cellGrid.get(i).add(current);
            }
        }
        addCellNeighbours();
    }

    /**
     * Set all the neighbours of every Cell in the grid.
     */
    private void addCellNeighbours(){
        for(int i=0; i<cellGrid.size(); i++){
            for(int j=0; j<cellGrid.get(0).size(); j++){
                Cell current = cellGrid.get(i).get(j);

                if(i==0){
                    //north, north east and north west neighbour will all be null
                    current.setNeighbour(NORTH, null);
                    current.setNeighbour(NORTH_EAST, null);
                    current.setNeighbour(NORTH_WEST, null);
                }
                else{
                    //north = i-1, north east = i-1, j+1 and north west = i-1, j-1
                    current.setNeighbour(NORTH, cellGrid.get(i-1).get(j));

                    if(j==0){
                        current.setNeighbour(NORTH_EAST,cellGrid.get(i-1).get(j+1));
                        current.setNeighbour(NORTH_WEST,null);
                    }
                    else if(j==(cellGrid.get(i).size()-1)){
                        current.setNeighbour(NORTH_EAST,null);
                        current.setNeighbour(NORTH_WEST,cellGrid.get(i-1).get(j-1));
                    }
                    else{
                        current.setNeighbour(NORTH_EAST, cellGrid.get(i-1).get(j+1));
                        current.setNeighbour(NORTH_WEST, cellGrid.get(i-1).get(j-1));
                    }
                }

                if(i==(cellGrid.size()-1)){
                    //south, south east and south west neighbour will all be null
                    current.setNeighbour(SOUTH, null);
                    current.setNeighbour(SOUTH_EAST, null);
                    current.setNeighbour(SOUTH_WEST, null);
                }
                else{
                    //south = i+1, south east = i+1, j+1 and south west = i+1, j-1
                    current.setNeighbour(SOUTH, cellGrid.get(i+1).get(j));

                    if(j==0){
                        current.setNeighbour(SOUTH_EAST, cellGrid.get(i+1).get(j+1));
                        current.setNeighbour(SOUTH_WEST, null);
                    }
                    else if(j==(cellGrid.get(i).size()-1)){
                        current.setNeighbour(SOUTH_EAST, null);
                        current.setNeighbour(SOUTH_WEST, cellGrid.get(i+1).get(j-1));
                    }
                    else{
                        current.setNeighbour(SOUTH_EAST, cellGrid.get(i+1).get(j+1));
                        current.setNeighbour(SOUTH_WEST, cellGrid.get(i+1).get(j-1));
                    }
                }

                if(j==0){
                    //west, north west and south west neighbour will all be null
                    current.setNeighbour(WEST, null);
                    current.setNeighbour(NORTH_WEST, null);
                    current.setNeighbour(SOUTH_WEST, null);
                }
                else{
                    //west = j-1, north west = i-1, j-1 and south west = i+1, j-1
                    current.setNeighbour(WEST, cellGrid.get(i).get(j-1));

                    if(i==0){
                        current.setNeighbour(NORTH_WEST, null);
                        current.setNeighbour(SOUTH_WEST, cellGrid.get(i+1).get(j-1));
                    }
                    else if(i == (cellGrid.size()-1)){
                        current.setNeighbour(NORTH_WEST, cellGrid.get(i-1).get(j-1));
                        current.setNeighbour(SOUTH_WEST, null);
                    }
                    else{
                        current.setNeighbour(NORTH_WEST, cellGrid.get(i-1).get(j-1));
                        current.setNeighbour(SOUTH_WEST, cellGrid.get(i+1).get(j-1));
                    }
                }

                if(j==(cellGrid.get(i).size()-1)){
                    //east, north east and south east neighbour will all be null
                    current.setNeighbour(EAST, null);
                    current.setNeighbour(NORTH_EAST, null);
                    current.setNeighbour(SOUTH_EAST, null);
                }
                else{
                    //east = j+1, north east = j+1, i-1 and south east = j+1, i+1
                    current.setNeighbour(EAST, cellGrid.get(i).get(j+1));

                    if(i==0){
                        current.setNeighbour(NORTH_EAST, null);
                        current.setNeighbour(SOUTH_EAST, cellGrid.get(i+1).get(j+1));
                    }
                    else if(i == (cellGrid.size()-1)){
                        current.setNeighbour(NORTH_EAST, cellGrid.get(i-1).get(j+1));
                        current.setNeighbour(SOUTH_EAST, null);
                    }
                    else{
                        current.setNeighbour(NORTH_EAST, cellGrid.get(i-1).get(j+1));
                        current.setNeighbour(SOUTH_EAST, cellGrid.get(i+1).get(j+1));
                    }
                }
            }
        }
    }

    /**
     * Perform an update on the isActive status of all Cells in the Grid.
     * This will be called every generation/iteration.
     * Will put 'new grid status' into a temporary grid before transferring data into cellGrid. This prevents the
     * changes from affecting the result.
     */
    public void updateCellGrid(){
        List<List<Integer>> tempGrid = new ArrayList<>();

        for(int i=0; i<cellGrid.size(); i++){
            tempGrid.add(new ArrayList<>());
            for(int j=0; j< cellGrid.get(i).size(); j++){
                Cell current = cellGrid.get(i).get(j);
                int activeNeighbours = current.getNumberOfActiveNeighbours();

                if(current.getIsActive()){
                    if(activeNeighbours ==3 || activeNeighbours ==2){
                        tempGrid.get(i).add(1);
                    }
                    else{
                        tempGrid.get(i).add(0);
                    }
                }
                else{
                    if(activeNeighbours == 3){
                        tempGrid.get(i).add(1);
                    }
                    else{
                        tempGrid.get(i).add(0);
                    }
                }
            }
        }

        for(int i=0; i<tempGrid.size(); i++){
            for(int j=0; j< tempGrid.get(i).size(); j++){
                Cell current = cellGrid.get(i).get(j);

                if(tempGrid.get(i).get(j) == 1){
                    current.setIsActive(true);
                }
                else{
                    current.setIsActive(false);
                }
            }
        }

        addCellNeighbours();
        generationNumber++;
    }
}