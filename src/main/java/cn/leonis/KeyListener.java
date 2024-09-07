package cn.leonis;

import java.io.IOException;

import org.jline.keymap.BindingReader;
import org.jline.keymap.KeyMap;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

public class KeyListener {
    public enum Operation {
        KEY_W, KEY_A, KEY_S, KEY_D, KEY_R, KEY_Q, UNKNOWN
    };

    private Terminal terminal;
    private KeyMap<Operation> keyMap;
    private NonBlockingReader reader;
    private BindingReader opReader;

    public KeyListener() throws IOException {
        terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        terminal.enterRawMode();
        reader = terminal.reader();
        keyMap = new KeyMap<>();
        bindKeys();
        opReader = new BindingReader(reader);
    }

    public Operation listen() {
        return opReader.readBinding(keyMap);
    }

    public static void main(String[] args) throws IOException {
        KeyListener listener = new KeyListener();
        while (true) {
            Operation op = listener.listen();
            if (op != Operation.KEY_Q)
                System.out.println(op);
            else
                break;
        }
    }

    private void bindKeys() {
        keyMap.bind(Operation.KEY_W, "W", "w");
        keyMap.bind(Operation.KEY_S, "S", "s");
        keyMap.bind(Operation.KEY_A, "A", "a");
        keyMap.bind(Operation.KEY_D, "D", "d");
        keyMap.bind(Operation.KEY_R, "R", "r");
        keyMap.bind(Operation.KEY_Q, "Q", "q");
        keyMap.setNomatch(Operation.UNKNOWN);
    }
}
