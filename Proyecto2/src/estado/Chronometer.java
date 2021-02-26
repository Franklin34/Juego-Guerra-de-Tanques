/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estado;

/**
 *
 * @author Usuario
 */
public class Chronometer {

    private long delta, lastTime;
    private long time;
    private boolean running;
    private boolean mori;

    public boolean getMori() {
        return mori;
    }

    public void setMori(boolean mori) {
        this.mori = mori;
    }

    public Chronometer() {
        delta = 0;
        lastTime = 0;
        running = false;
        mori = false;
    }

    public void run(long time) {
        running = true;
        this.time = time;
    }

    public void update() {
        if (running) {
            delta += System.currentTimeMillis() - lastTime;
        }
        if (delta >= time) {
            running = false;
            delta = 0;
        }

        lastTime = System.currentTimeMillis();
    }

    public boolean isRunning() {
        return running;
    }

}
