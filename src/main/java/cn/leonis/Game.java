package cn.leonis;

import static java.util.Map.entry;

import java.io.IOException;
import java.util.Map;

import cn.leonis.Board.Direction;
import cn.leonis.KeyListener.Operation;

public class Game {
    private Board board;
    private KeyListener keyListener;

    private int currentScore;
    private int bestScore;
    private boolean running;

    private static final Map<Operation, Direction> readDirection = Map.ofEntries(
            entry(Operation.KEY_W, Direction.NORTH),
            entry(Operation.KEY_S, Direction.SOUTH),
            entry(Operation.KEY_A, Direction.WEST),
            entry(Operation.KEY_D, Direction.EAST));

    public Game() throws IOException {
        board = new Board();
        keyListener = new KeyListener();
    }

    private void init() {
        board = new Board();
        System.out.println(board);
    }

    private void update() {
        Operation op = keyListener.listen();
        System.out.println(op);
        if (op == Operation.UNKNOWN) {
            return;
        }

        if (op == Operation.KEY_R) {
            init();
            return;
        }

        if (op == Operation.KEY_Q) {
            running = false;
            return;
        }

        Direction direction = readDirection.get(op);
        board.oneTurn(direction);
        System.out.print(board);
        System.out.print("\n");
    }

    private void exit() {
        return;
    }

    private void start() {
        running = true;
        init();
        while (running) {
            update();
        }
        exit();
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.start();
    }
}
