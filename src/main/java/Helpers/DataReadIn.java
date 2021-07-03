package Helpers;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for reading in user data.
 * Data will be obtained from a specified csv file path. If it cannot be obtained then an error will display to the user.
 * Data will be read in via strings before being parsed to integers.
 * This allows the data to be passed to the responsible controller where it can be acted upon.
 */
public class DataReadIn {

    //Constants
    /**
     * The number of rows/columns which should be included as a buffer zone within the grid.
     */
    private final int BUFFER = 5;
    /**
     * File name of the data to be read in.
     */
    private final String FILE_NAME="SeedData.csv";

    //Instance Variables
    private List<String> rows;
    private List<List<Integer>> gridData;
    private boolean isObtained = false;

    /**
     * Constructor for DataReadIn.
     * Will set the path to the csv file the user has prepared
     * and will then setup the grid data should the file be found.
     */
    public DataReadIn(){
        rows = new ArrayList<>();
        gridData = new ArrayList<>();
        readInCSVFile();
        if(isObtained){
            setGridData();
        }
    }

    /**
     * Will read in the file the user has specified.
     * Will scan this into a list in the form of a string which can later be operated on.
     * Method is also responsible for ensuring the program is aware if the file has been found or not.
     */
    private void readInCSVFile(){
        try{
            Scanner csvScanner = new Scanner(new FileReader(FILE_NAME));
            while(csvScanner.hasNext()){
                rows.add(csvScanner.next());
            }
            csvScanner.close();
            isObtained = true;
        }
        catch (Exception e){
            System.err.println("Cannot find file");
            isObtained = false;
        }
    }

    /**
     * Getter for grid data.
     * Will return this in the form of a series of lists of integers stored within one list.
     * @return the list of the int data for the grid
     */
    public List<List<Integer>> getGridData(){
        return gridData;
    }

    /**
     * Setter for grid data.
     * Uses the data initially read in as a list of Strings.
     * The list of strings will be separated to locate each data point before being converted into integers.
     * A buffer zone is added to the top, bottom and sides of the grid. This allows for objects to 'leave' the visible
     * grid zone. Allows demonstration that the grid is infinite.
     */
    public void setGridData(){
        List<List<String>> data = new ArrayList<>();
        for (String row:rows){
            String[] result = row.split(",");
            data.add(new ArrayList<>());
            for(String t: result){
                data.get(data.size()-1).add(t);
            }
        }

        for(int i = 0; i< BUFFER; i++){
            gridData.add(new ArrayList<>());
            for(int j = 0; j < (data.get(0).size()+(BUFFER*2)); j++){
                gridData.get(i).add(0);
            }
        }

        for(int i = 0; i < data.size(); i++){
            gridData.add(new ArrayList<>());

            for(int j = 0; j < data.get(i).size(); j++){
                if (j == 0){
                    for(int k = 0; k < BUFFER; k++){
                        gridData.get(i+BUFFER).add(0);
                    }
                }

                gridData.get(i+BUFFER).add(Integer.parseInt(data.get(i).get(j)));

                if (j == (data.get(i).size()-1)){
                    for(int k = 0; k < BUFFER; k++){
                        gridData.get(i+BUFFER).add(0);
                    }
                }
            }
        }

        for(int i = gridData.size(); i< (data.size() + (BUFFER*2)); i++){
            gridData.add(new ArrayList<>());
            for(int j = 0; j < (data.get(0).size()+(BUFFER*2)); j++){
                gridData.get(i).add(0);
            }
        }
    }

    /**
     * Getter for isObtained.
     * IsObtained is set earlier when the data is either successfully read into the program or
     * not.
     * @return boolean value for whether data has been successfully read in or not
     */
    public boolean getIsObtained(){
        return isObtained;
    }
}
