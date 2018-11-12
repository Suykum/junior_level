package ru.job4j.threads;
import javafx.scene.shape.Rectangle;
public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        boolean flag = true;
        while (true) {
            if (rect.getX() == 300) {
                flag = false;
            } else if (rect.getX() == 0) {
                flag = true;
            }

            if (flag) {
                this.rect.setX(this.rect.getX() + 1);
            } else {
                this.rect.setX(this.rect.getX() - 1);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
