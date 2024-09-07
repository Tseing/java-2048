package cn.leonis;

public class Tile {
    private int value;

    private static final int NEW_TILE_VAL = 2;
    private static final int NULL_TILE_VAL = 0;

    public Tile(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setNew() {
        value = NEW_TILE_VAL;
    }

    public void setNull() {
        value = NULL_TILE_VAL;
    }

    public boolean isNull() {
        return value == 0;
    }

    public String toString() {
        return Integer.toString(value);
    }

    public boolean equals(Tile tile) {
        return value == tile.value;
    }
}
