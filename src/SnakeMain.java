public class SnakeMain {
    public static void main(String[] args) {
        GameBoard g = new GameBoard(100, 100);
        for(int i = 0; i < 10; i++) {
            g.respawnFood();
        }
    }
}
