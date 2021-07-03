package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cell class which tracks active status of cell and tracks all the cell's neighbours.
 */
public class Cell {

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
    private boolean isActive;
    private int numberOfActiveNeighbours;
    private Map<String, Cell> allNeighbours;

    /**
     * Cell constructor with no parameters.
     * Generates a cell which is not active and has no active neighbours.
     */
    public Cell(){
        isActive = false;
        numberOfActiveNeighbours = 0;
        allNeighbours = new HashMap<String, Cell>();
        setupNeighbours();
    }

    /**
     * Gets the status of whether this cell is active.
     * @return boolean: isActive - status of alive/dead of cell.
     */
    public boolean getIsActive(){
        return isActive;
    }

    /**
     * Sets the status of isActive to the new status passed in.
     * @param newStatus boolean containing new status of the cell.
     */
    public void setIsActive(boolean newStatus){
        isActive = newStatus;
    }

    /**
     * Gets the number of currently active neighbours for this cell.
     * @return int which contains the number of active neighbours.
     */
    public int getNumberOfActiveNeighbours(){
        numberOfActiveNeighbours = 0;

        for(Map.Entry neighbour : allNeighbours.entrySet()){
            Cell test = (Cell) neighbour.getValue();
            if(test != null && test.getIsActive()){
                numberOfActiveNeighbours++;
            }
        }

        return numberOfActiveNeighbours;
    }

    /**
     * Adds all neighbour positions and a null reference to map of all neighbours.
     */
    private void setupNeighbours(){
        allNeighbours.put(NORTH, null);
        allNeighbours.put(NORTH_EAST, null);
        allNeighbours.put(EAST, null);
        allNeighbours.put(SOUTH_EAST, null);
        allNeighbours.put(SOUTH, null);
        allNeighbours.put(SOUTH_WEST, null);
        allNeighbours.put(WEST, null);
        allNeighbours.put(NORTH_WEST, null);
    }

    /**
     * Gets the neighbouring cell at the position in the parameters.
     * @param position - string containing position of desired neighbour.
     * @return the neighbouring cell from parameter position.
     */
    public Cell getNeighbour(String position){
        return allNeighbours.get(position);
    }

    /**
     * Sets the neighbouring cell at the position stated in the parameters and sets it to the new cell passed in.
     * @param position - string containing position of desired neighbour.
     * @param neighbour - cell which is new neighbour.
     */
    public void setNeighbour(String position, Cell neighbour){
        allNeighbours.replace(position, allNeighbours.get(position), neighbour);
    }
}