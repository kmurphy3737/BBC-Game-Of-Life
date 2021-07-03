package Views;

import Controllers.GridController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * View for GridController.
 * Maintains what is displayed to the user through the use of a JFrame.
 */
public class GridView implements ActionListener{

    //Constants
    /**
     * Title to be displayed at the top of the JFrame.
     */
    private final String FRAME_TITLE = "Game of Life";
    /**
     * String to be displayed in the generationLabel.
     */
    private final String GENERATION = "Generation: ";
    /**
     * String to be displayed in the start button.
     */
    private final String START = "Start";
    /**
     * String to be displayed in the start button when user has already paused the game.
     */
    private final String RESUME = "Resume";
    /**
     * String to be displayed in the pause button.
     */
    private final String PAUSE = "Pause";
    /**
     * String to be displayed in the stop button.
     */
    private final String STOP = "Stop";
    /**
     * Width of the JFrame.
     */
    private final int FRAME_SIZE_WIDTH = 1000;
    /**
     * Height of the JFrame.
     */
    private final int FRAME_SIZE_HEIGHT = 1000;


    //Instance Variables
    private GridController controller;
    private JFrame frame;
    private JPanel mainPanel, northPanel, southPanel, centerPanel;
    private JButton startButton, pauseButton, stopButton;
    private JLabel generationNumberLabel;
    private List<List<JPanel>> cellGrid;
    private GridPanel grid;

    /**
     *Constructor which accepts the GridController for this GridView.
     * Will setup the JFrame with all its components and ensure it appears in the middle of the screen.
     * @param controller is the controller that should be used to manipulate this view
     */
    public GridView(GridController controller){
        this.controller = controller;
        cellGrid = new ArrayList<>();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    UIManager.setLookAndFeel((UIManager.getSystemLookAndFeelClassName()));
                }
                catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                frame = new JFrame(FRAME_TITLE);
                frame.setSize(FRAME_SIZE_WIDTH,FRAME_SIZE_HEIGHT);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setupPanels();
                frame.add(mainPanel);
                setupComponents();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    /**
     * Setup the panels to be displayed on the JFrame.
     */
    private void setupPanels(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        centerPanel = new JPanel();
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        grid = new GridPanel();
        centerPanel.add(grid, BorderLayout.CENTER);

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Setup the components to be displayed on the JFrame.
     */
    private void setupComponents(){
        setupLabels();
        setupButtons();
    }

    /**
     * Setup the labels to be displayed on the JFrame.
     */
    private void setupLabels(){
        generationNumberLabel = new JLabel(GENERATION + 0);
        northPanel.add(generationNumberLabel);
    }

    /**
     * Setup the buttons to be displayed on the JFrame.
     */
    private void setupButtons(){
        startButton = new JButton(START);
        startButton.addActionListener(this);

        pauseButton = new JButton(PAUSE);
        pauseButton.addActionListener(this);
        pauseButton.setEnabled(false);

        stopButton = new JButton(STOP);
        stopButton.addActionListener(this);
        stopButton.setEnabled(false);

        southPanel.add(startButton);
        southPanel.add(pauseButton);
        southPanel.add(stopButton);
    }

    /**
     * Displays the updated grid data and generation number on the JFrame.
     */
    public void refreshGrid(){
        if(startButton.isEnabled()){
            startButton.setEnabled(false);
            pauseButton.setEnabled(true);
            stopButton.setEnabled(true);
        }
        grid.repaint();
        generationNumberLabel.setText(GENERATION + controller.getGenerationNumber());
    }

    /**
     * Displays the paused grid data and buttons on the JFrame.
     */
    public void pauseGame(){
        startButton.setText(RESUME);
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);
        grid.repaint();
    }

    /**
     * Displays seed data on grid on the JFrame and resets button names and status.
     */
    public void stopGame(){
        if(startButton.getText().equals(RESUME)){
            startButton.setText(START);
        }
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        grid.repaint();
        generationNumberLabel.setText(GENERATION + controller.getGenerationNumber());
    }

    /**
     * Identifies the button clicked by user and triggers appropriate response from controller.
     * @param e ActionEvent sent when button was clicked
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == startButton){
            controller.startGame();
        }
        else if(e.getSource() == pauseButton){
            controller.pauseGame();
        }
        else if(e.getSource() == stopButton){
            controller.stopGame();
        }
    }

    /**
     * Custom JPanel to draw the grid.
     */
    public class GridPanel extends JPanel{

        //Constants
        /**
         * Base-coordinate for drawing grid.
         */
        private final int BASE_COORDINATE = 5;
        /**
         * Width of GridPanel.
         */
        private final int PANEL_SIZE_WIDTH = 500;
        /**
         * Height of GridPanel.
         */
        private final int PANEL_SIZE_HEIGHT = 500;

        //Instance Variables
        private List<List<Integer>> gridData;

        /**
         * Constructor for Grid Panel with no parameters.
         * Sets gridData and the layout of the panel.
         */
        public GridPanel(){
            gridData = controller.getGridData();
            int rows = gridData.size();
            int columns = gridData.get(0).size();
            this.setLayout(new GridLayout(rows, columns));
        }

        /**
         * Getter for preferredSize.
         * @return new Dimension with the width and height of preferred size of GridPanel
         */
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(PANEL_SIZE_WIDTH, PANEL_SIZE_HEIGHT);
        }

        /**
         * Draws the grid on the GridPanel.
         * Ignores the buffer zone set in DataReadIn.
         * Displays a filled box where cell is active.
         * @param g is Graphics object which is passed in.
         */
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            int xCoordinates = BASE_COORDINATE;
            int yCoordinates = BASE_COORDINATE;
            int width = (this.getWidth()-BASE_COORDINATE) / (gridData.get(0).size()-10);
            int height = (this.getHeight()-BASE_COORDINATE) / (gridData.size()-10);

            for(int i = 5; i < (gridData.size()-5); i++){
                for(int j = 5; j <  (gridData.get(0).size()-5); j++){
                    Rectangle box = new Rectangle(xCoordinates,yCoordinates,width,height);
                    g2.draw(box);
                    if(gridData.get(i).get(j) == 1){
                        g2.fill(box);
                    }
                    xCoordinates += width;
                }
                xCoordinates = BASE_COORDINATE;
                yCoordinates += height;
            }
        }
    }
}