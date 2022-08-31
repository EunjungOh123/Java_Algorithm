package 자바의_정석;

public class include_extends {
    public static void main(String[] args) {

    }
}

class Point {
    int x;
    int y;

    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }
    Point() {
        this(0, 0);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

