import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A -> MyFrame.gameLogic.p1.setLeftPressed(true);
            case KeyEvent.VK_D -> MyFrame.gameLogic.p1.setRightPressed(true);
            case KeyEvent.VK_LEFT -> MyFrame.gameLogic.p2.setLeftPressed(true);
            case KeyEvent.VK_RIGHT -> MyFrame.gameLogic.p2.setRightPressed(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A -> MyFrame.gameLogic.p1.setLeftPressed(false);
            case KeyEvent.VK_D -> MyFrame.gameLogic.p1.setRightPressed(false);
            case KeyEvent.VK_LEFT -> MyFrame.gameLogic.p2.setLeftPressed(false);
            case KeyEvent.VK_RIGHT -> MyFrame.gameLogic.p2.setRightPressed(false);
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE && MyFrame.gameLogic.isFinished()){
            MyFrame.gameLogic = new GameLogic();
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            MyFrame.gameLogic.togglePause();
        }
        if(e.getKeyCode() == KeyEvent.VK_R){
            MyFrame.gameLogic = new GameLogic();
        }

        if(e.getKeyCode() == KeyEvent.VK_S){
            MyFrame.gameLogic.p1.activateDefensivePowerUp();
        }
        if(e.getKeyCode() == KeyEvent.VK_W && MyFrame.gameLogic.p1.isOffensivePowerUpCharged() && MyFrame.gameLogic.arePowersEnabled()){
            MyFrame.gameLogic.p1.activateOffensivePowerUp();
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            MyFrame.gameLogic.p2.activateDefensivePowerUp();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP && MyFrame.gameLogic.p2.isOffensivePowerUpCharged() && MyFrame.gameLogic.arePowersEnabled()){
            MyFrame.gameLogic.p2.activateOffensivePowerUp();
        }
    }
}
