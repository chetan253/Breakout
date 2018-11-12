package com.UI;


import com.entity.Brick;

public class SquareStrategy implements Strategy {
    private Brick[] bricks;
    public SquareStrategy(Brick[] bricks){
        this.bricks = bricks;
        this.setObjects();
    }


    @Override
    public void setObjects() {
        int[] xs = new int[]{200,200,300,400, 400};
        int[] ys = new int[]{50,200,125,50, 200};
        for (int i = 0; i < bricks.length; i++) {

            bricks[i] = new Brick(xs[i], ys[i]);
            bricks[i].setFlag(true);
        }
    }
}
