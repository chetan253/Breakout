package com.UI;

import com.entity.Brick;

public class WedgeStrategy implements Strategy {
    private Brick[] bricks;
    public WedgeStrategy(Brick[] bricks){
        this.bricks = bricks;
        this.setObjects();
    }


    @Override
    public void setObjects() {
        int[] xs = new int[]{190,250,310,370, 430};
        int[] ys = new int[]{150,90,30, 90, 150};
        for (int i = 0; i < bricks.length; i++) {

            bricks[i] = new Brick(xs[i], ys[i]);
            bricks[i].setFlag(true);
        }
    }
}
