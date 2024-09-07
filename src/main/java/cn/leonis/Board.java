package cn.leonis;

import java.util.ArrayList;
import java.util.Random;

public class Board {

    public enum Direction {
        NORTH, SOUTH, WEST, EAST
    };

    private Tile[][] boardData;
    private Random random = new Random();
    private Tile[] tempLine;

    private static final int M = 4;

    public Board() {
        boardData = new Tile[M][M];
        tempLine = new Tile[M];

        for (int i = 0; i < boardData.length; i++)
            for (int j = 0; j < boardData[0].length; j++) {
                boardData[i][j] = new Tile(0);
            }

        addTile();
    }

    public static void main(String[] args) {
        Board board = new Board();
        System.out.print(board);
        System.out.println("*********************************");
        board.swipe(Direction.NORTH);
        System.out.print(board);
        System.out.println("*********************************");
        board.addTile();
        System.out.print(board);
        System.out.println("*********************************");
        board.swipe(Direction.WEST);
        System.out.print(board);
        System.out.println("*********************************");
    }

    public void addTile() {
        ArrayList<Tile> nullTiles = new ArrayList();

        for (int r = 0; r < M; r++)
            for (int c = 0; c < M; c++) {
                if (boardData[r][c].isNull())
                    nullTiles.add(boardData[r][c]);
            }

        int idx = random.nextInt(nullTiles.size());
        nullTiles.get(idx).setNew();
    }

    public void swipe(Direction direction) {
        swipeMatrix(direction);
    }

    public void oneTurn(Direction direction) {
        swipe(direction);
        addTile();
    }

    private void swipeMatrix(Direction direction) {
        switch (direction) {
            case NORTH: {
                for (int i = 0; i < M; i++) {
                    for (int j = 0; j < M; j++)
                        tempLine[j] = boardData[j][i];

                    Board.swipeLine(tempLine);
                }
            }
                break;

            case SOUTH: {
                for (int i = 0; i < M; i++) {
                    for (int j = M - 1; j >= 0; j--)
                        tempLine[M - j - 1] = boardData[j][i];

                    Board.swipeLine(tempLine);
                }
            }
                break;

            case WEST: {
                for (int i = 0; i < M; i++) {
                    for (int j = 0; j < M; j++)
                        tempLine[j] = boardData[i][j];

                    Board.swipeLine(tempLine);
                }
            }
                break;

            case EAST: {
                for (int i = 0; i < M; i++) {
                    for (int j = M - 1; j >= 0; j--)
                        tempLine[M - j - 1] = boardData[i][j];

                    Board.swipeLine(tempLine);
                }
            }
                break;

            default:
                break;
        }

    }

    // Swipe all tiles in array to left
    private static void swipeLine(Tile[] tiles) {
        // Tile to place next value
        int placeP = 0;
        int checkP = 0;
        int compP = checkP + 1;

        while (compP < tiles.length) {
            if (tiles[checkP].isNull()) {
                checkP += 1;
                compP = checkP + 1;
                continue;
            }

            if (tiles[compP].isNull()) {
                compP += 1;
                continue;
            }

            // Merge 2 tiles
            if (tiles[checkP].equals(tiles[compP])) {
                int mergedValue = tiles[compP].getValue() * 2;
                tiles[checkP].setNull();
                tiles[compP].setNull();
                tiles[placeP].setValue(mergedValue);

                checkP += 1;
                compP = checkP + 1;
                placeP += 1;
            }
            // Cannot merge. Move tile to correct place.
            else {
                int movedValue = tiles[checkP].getValue();
                tiles[checkP].setNull();
                tiles[placeP].setValue(movedValue);

                checkP += 1;
                compP = checkP + 1;
                placeP += 1;
            }
        }

        int movedValue = tiles[checkP].getValue();
        tiles[checkP].setNull();
        tiles[placeP].setValue(movedValue);

    }


    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Tile[] row : boardData) {
            for (Tile tile : row) {
                stringBuilder.append(tile);
                stringBuilder.append("\t");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();

    }
}