package edu.eci.arsw.highlandersim;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Immortal extends Thread {

    private ImmortalUpdateReportCallback updateCallback = null;

    private AtomicInteger health;

    private int defaultDamageValue;

    private final List<Immortal> immortalsPopulation;

    private final String name;

    private final Random r = new Random(System.currentTimeMillis());

    public boolean flag = false;

    public boolean alive = true;


    public Immortal(String name, List<Immortal> immortalsPopulation, AtomicInteger health, int defaultDamageValue, ImmortalUpdateReportCallback ucb) {
        super(name);
        this.updateCallback = ucb;
        this.name = name;
        this.immortalsPopulation = immortalsPopulation;
        this.health = health;
        this.defaultDamageValue = defaultDamageValue;
    }

    public void run() {

        while (alive) {
            Immortal im;
            synchronized (immortalsPopulation) {
                if (immortalsPopulation.size() < 3) {
                    updateCallback.processReport(immortalsPopulation.get(0).toString() + "  " + immortalsPopulation.get(1).toString() + "FIN");
                    break;
                } else {
                    int myIndex = immortalsPopulation.indexOf(this);
                    int nextFighterIndex = r.nextInt(immortalsPopulation.size());
                    boolean temp = true;
                    while (temp) {
                        //nextFighterIndex = r.nextInt(immortalsPopulation.size());
                        if (nextFighterIndex == myIndex) {
                            nextFighterIndex = r.nextInt(immortalsPopulation.size());
                        } else {
                            temp = false;
                        }
                    }
                    //avoid self-fight

                    im = immortalsPopulation.get(nextFighterIndex);
                }
            }
            if (alive) {
                this.fight(im);
            }


            synchronized (this) {
                while (flag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public synchronized void halt() {
        flag = true;
        this.notifyAll();
    }

    public synchronized void restart() {
        flag = false;
        this.notifyAll();
    }

    public void fight(Immortal i2) {
        boolean flag2 = false;
        synchronized (i2) {
            if (i2.health.get()>0) {
                flag2=true;
                i2.changeHealth(i2.getHealth().get() - defaultDamageValue);
                this.health.set(this.health.get() + defaultDamageValue);
                if (i2.health.get() <= 0) {
                    i2.immortalsPopulation.remove(i2);
                    i2.flag = false;
                    i2.alive = false;
                }
            }
        }
        synchronized (updateCallback) {
            if (flag2) {
                updateCallback.processReport("Fight: " + this + " vs " + i2 + "\n");
            } else {
                updateCallback.processReport(this + " says:" + i2 + " is already dead!\n");
            }
        }
    }

    public synchronized void changeHealth(int v) {
        health.set(v);
        //this.notify();
    }

    public AtomicInteger getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return name + "[" + health + "]";
    }

}
