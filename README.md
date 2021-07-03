# GameOfLife
 
The data which is displayed on the grid is set by the csv file: SeedData.csv. This can be found in the root directory of the project. Users can edit this to display the grid they wish in csv format. The “Gosper glider gun” data has been preloaded for convenience.

Once the program has been run, the user will be able to “Start” the iterations and pause/resume as they see fit. The user is also able to “Stop” and begin the iterations again from the start.

I have made the following assumptions about the problem/assessment. These are as follows:
1.	That the user will supply the data for the grid in the form of a csv file. This has already been created and coded into the solution for ease. Meaning any testing of different grid sizes etc can be done through editing this csv file.
2.	That while the Game of Life is set in an infinite grid, the program will display a finite ‘snapshot’ of the grid (provided by user entry into csv file). Meaning should active cells move out of the available visible area then the program will not follow them.
