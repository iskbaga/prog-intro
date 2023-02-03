package game;

public class MNKBoard implements Board, Position {


    private final Cell[][] cells;
    private final int m;
    private final int n;
    private final int k;
    private int nonEmptyCells = 0;
    private Cell turn;

    public MNKBoard(int m, int n, int k) {
        this.cells = new Cell[m][n];
        this.m = m;
        this.n = n;
        this.k = k;
        turn = Cell.X;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i==j && n==m){
                    addObstacle(i,i);
                } else if(i+j+1==n && n==m) {
                    addObstacle(i,j);
                } else{
                    cells[i][j] = Cell.E;
                }
            }
        }
    }

    @Override
    public Position getPosition() {
        return new PlayerPosition(this);
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    private int numberOfCells(int row, int column, int i, int j) {
        int cnt = 0;
        while (row + i >= 0 && column + j >= 0
                && row + i < m
                && column + j < n
                && cells[row + i][column + j].equals(turn)) {
            cnt++;
            row += i;
            column += j;
        }
        return cnt;
    }
    public void addObstacle(int x, int y){
        if(x<=n && y<=m){
            cells[x][y]=Cell.P;
            nonEmptyCells++;
        }
    }

    @Override
    public Result makeMove(final Move move) {
        if (!(isBoardCell(move) && isEmptyCell(move))) {
            return Result.LOSE;
        }

        int r = move.getRow(), c = move.getColumn();
        cells[r][c] = move.getValue();

        if (isWinningMove(r, c)) {
            return Result.WIN;
        }

        if (nonEmptyCells+1 >= n*m) {
            return Result.DRAW;
        }

        if(turn==Cell.X){
            turn=Cell.O;
        } else{
            turn=Cell.X;
        }
        nonEmptyCells++;
        return Result.UNKNOWN;
    }

    private boolean isWinningMove(int r, int c) {
        return (numberOfCells(r, c, 0, 1) + numberOfCells(r, c, 0, -1) + 1) >= k
                ||(numberOfCells(r, c, 1, 0) + numberOfCells(r, c, -1, 0) + 1) >= k
                ||(numberOfCells(r, c, 1, 1) + numberOfCells(r, c, -1, -1) + 1) >= k
                ||(numberOfCells(r, c, -1, 1) + numberOfCells(r, c, 1, -1) + 1) >= k;
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getN() {
        return n;
    }



    public boolean isBoardCell(final Move move){
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n;
    }
    public boolean isEmptyCell(final Move move){
        return cells[move.getRow()][move.getColumn()].equals(Cell.E);
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Cell[] cell : cells) {
            for (int c = 0; c < n; c++) {
                if(cell[c]==Cell.E){
                    sb.append(".");
                } else {
                    sb.append(cell[c]);
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
