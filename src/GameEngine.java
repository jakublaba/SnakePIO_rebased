import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.*;

public class GameEngine {
    private boolean state;
    private GameBoard myGameBoard;
    //private GamePanel myGamePanel;
    //private GameFrame myGameFrame;

    public GameEngine() {
        state = false;
        myGameBoard = new GameBoard(); //tworzy gejmborda ze snejkiem
    }

    public boolean returnState() {
        return state;
    }

    public void changeState(boolean logicalValue) {
        this.state = logicalValue;
    }

    private void updateGame() {
        myGameBoard.checkBorderCollision(20);
        myGameBoard.checkTailCollision(20);

        if (myGameBoard.checkFood(20)) {
            myGameBoard.respawnFood();
            myGameBoard.mySnake.addBodySegment();

            //do sprawdzania pozycji części snejka
            /*
            System.out.printf("SIZE: (%d)\n", GameBoard.snake.bodySegments.size());
            for (int i = GameBoard.snake.bodySegments.size() - 1; i > 1; i--) {
                System.out.printf("Segment %d at (%f, %f) location\n", i, GameBoard.snake.bodySegments.get(i).getX(),
                        GameBoard.snake.bodySegments.get(i).getY());
            }
            */
        }
        GameBoard.mySnake.move();
    }

    /* to jest dopiero szkielet/prototyp */
    public void run(boolean isRunning) {
        final int MS_PER_FRAME = 16; // około 60 FPS (troche mniej)

        //pętla (jeżeli gra trwa to kontynuujemy
        while (isRunning == true) {
            long start = currentTimeMillis();

            //sprawdz kierunek (kontrolsy), może to jakoś oddzielnie się dzieje idk xd zobaczymy

            updateGame();
            //apdejtuj

            //renderuj

            try {
                sleep(start + MS_PER_FRAME - currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
