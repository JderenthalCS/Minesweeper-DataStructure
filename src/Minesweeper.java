public class Minesweeper {

    // Data members
    private char[][] board;   // The game board where cells will be displayed
    private boolean[][] mines; // Array to track the locations of mines
    private boolean[][] revealed; // Array to track which cells have been revealed
    private int rows; // Number of rows in the board
    private int cols; // Number of columns in the board
    private int numMines; // Number of mines in the game
    private boolean gameOver; // Boolean to check if the game is over

    // Constructor to initialize the board with the specified dimensions and number of mines
    public Minesweeper(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.board = new char[rows][cols];
        this.mines = new boolean[rows][cols];
        this.revealed = new boolean[rows][cols];
        this.gameOver = false;

            initializeBoard();
            placeMines();
            calculateNumbers();

        // Call methods to initialize the board and place mines
    }
    public boolean getGameOver(){
        return this.gameOver;
    }
    public void setGameOver(boolean status)
    {
        this.gameOver = status;

    }
    // Method to initialize the game board with empty values
    private void initializeBoard() {
        // TODO: Implement this method
        // Iterating through [][], placing dashes
        for (int i = 0; i<rows; i++){
            for(int j =0; j < cols; j++){
                board[i][j] = '-';
                revealed[i][j] = false;
                //All elements are unrevealed at start of game
            }
        }
    }

    // Method to randomly place mines on the board
    private void placeMines() {
        // TODO: Implement this method
        int minePlaced = 0; //initializing

        //while current minePlaced is less than the total (numMines) of mines on the board...
        while(minePlaced < numMines){
            //numMines currently equals 10 (10,10,x).

            //Pulling a random coordinate
            int row = (int)(Math.random()*rows);
            int col = (int)(Math.random()*cols);

            //... Place a mine by creating mines[][](coordinate) = true
            if(!mines[row][col]){
                mines[row][col] = true;
                minePlaced++; //Increasing minePlaced, to avoid infinite loop.
            }
        }
    }

    // Method to calculate numbers on the board for non-mine cells
    private void calculateNumbers() {
        // TODO: Implement this method
        //iterating through the board, if no mines present...
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!mines[i][j]) {
                    int count =0;
                    //No mines present count = 0

                    //iterating through adjescent cells [i-1,i+1 ... etc], checking for mines
                    for(int aRow = i-1; aRow <=i+1; aRow++){
                        for(int aCol = j-1; aCol <= j+1; aCol++){
                            //neccessary bounds ensuring no cells are placed outside of the 10x10 grid
                            //rows and cols are pulled from the instantiation of the object within main (x,x,10)
                                if(aRow >= 0 && aRow < rows && aCol >= 0 && aCol < cols && mines[aRow][aCol])
                                        count++;
                                //Increments when a mine has been found.
                                    }
                            }
                board[i][j] = (char) (count + '0');
                    //Marks board with 0 if no mines are found or the count, if mines
                    //are present.
            }
        }
        }
    }

    // Method to display the current state of the board
    public void displayBoard() {
        // TODO: Implement this method
        //iterates through board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                // Reveals coordinate
                if (revealed[i][j]) {
                    System.out.print(board[i][j] + " ");
                }

                // Printing F's when action = flag
                else if (board[i][j] == 'F') {
                    System.out.print("F ");
                }

                //Printing all dashes on board
                else {
                    System.out.print("- ");
                }
            }
            //creates new line after each row
            System.out.println();
        }
    }

    // Method to handle a player's move (reveal a cell or place a flag)
    public void playerMove(int row, int col, String action) {
        // TODO: Implement this method
        //coordinates action with respectable method
        //if reveal then revealCell(x,y)
            if(action.equals("reveal")){
                if(mines[row][col]){
                    setGameOver(true);
                    System.out.println("You hit a mine! Game Over!!!");
                } else{
                    revealCell(row, col);
                }
                //If flag then flagCell(x,y)
            }else if (action.equals("flag")){
                flagCell(row,col);
                //If unflag then unflagCell(x,y)
            }else if (action.equals("unflag")){
                unflagCell(row, col);
            }
    }

    // Method to check if the player has won the game
    public boolean checkWin() {
        // TODO: Implement this method
        //Iterating through the board, if mines have not been struck, and the board is not revealed, no win
        for(int i=0; i< rows; i++){
            for(int j =0; j< cols; j++){
                if(!mines[i][j] && !revealed[i][j]){
                    return false;
                }
            }
        }
        //If board has iterated, and no mines struck, and board is revealed, WIN
        return true;
    }

    // Method to check if the player has lost the game
    public boolean checkLoss(int row, int col) {
        // TODO: Implement this method
        return mines[row][col];
        //If player chooses mine coordinates then player loses
    }

    // Method to reveal a cell (and adjacent cells if necessary)
    private void revealCell(int row, int col) {
        // TODO: Implement this method
        //If statement ensuring bounds, and cell isnt already revealed... if bounds are broken exit method
        //re-ask for row/col/action
        if(row<0 || row>= rows|| col <0 || col >= cols || revealed[row][col]){
            return;
        }
        //if mine is revealed mark as astrisk and exit method ( returning and ending game)
            revealed[row][col] = true;
            if(mines[row][col]) {
                board[row][col] = '*';
                return;
            }
            //iterating through board revealing adjescent cells if current cell includes zero '0'
            if(board[row][col] == '0'){
                for (int i = row - 1; i <= row +1 ; i++) {
                    for (int j = col -1; j <= col +1; j++) {
                        if (i >= 0 && i < rows && j>= 0 && j < cols && !revealed[i][j]) {
                            revealCell(i, j); //reveals neighbor cell
                        }
                    }
                }
            }}

    // Method to flag a cell as containing a mine
    private void flagCell(int row, int col) { //WORKS
        // TODO: Implement this method
        //if coord not revealed place F for flag
                if(!revealed[row][col]){
                    board[row][col] = 'F'; //F = flag
                }
    }

    // Method to unflag a cell
    private void unflagCell(int row, int col) { //WORKS
        // TODO: Implement this method
        //if F has been placed, replace with -
                if(board[row][col] == 'F'){
                    board[row][col] = '-';
                }
    }
}
