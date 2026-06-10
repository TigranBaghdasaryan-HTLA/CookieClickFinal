import java.util.Timer;
import java.util.TimerTask;

public class Game {
    protected int cookieCounter;
    private int cookiesPerClick;
    private int cookiesPerSecond;

    // Cost Tracking for 8 Upgrades
    protected int upgradeCost1;
    protected int upgradeCost2;
    protected int upgradeCost3;
    protected int upgradeCost4;
    protected int upgradeCost5;
    protected int upgradeCost6;
    protected int upgradeCost7;
    protected int upgradeCost8;

    private boolean frenzyActive;
    private int frenzyMultiplier;

    public Game() {
        cookieCounter = 0;
        cookiesPerClick = 1;
        cookiesPerSecond = 0;
        frenzyActive = false;
        frenzyMultiplier = 1;

        // Initial costs
        upgradeCost1 = 15;   // Clicks +1
        upgradeCost2 = 100;  // Grandma (Auto +1/sec)
        upgradeCost3 = 500;  // Oven (Auto +5/sec)
        upgradeCost4 = 1000; // Factory (Auto +20/sec)
        upgradeCost5 = 2500; // Click Frenzy (+5 cookies per click for 10s)
        upgradeCost6 = 5000; // Golden Cookie (+15 cookies per click permanently)
        upgradeCost7 = 10000;// Lab (Auto +100/sec)
        upgradeCost8 = 50000;// Portal (Auto +500/sec)

        // Timer for Cookies Per Second (runs every 1 second)
        Timer slowCooker = new Timer();
        TimerTask slow = new TimerTask() {
            @Override
            public void run() {
                // Apply frenzy multiplier during calculations if active
                cookieCounter += (cookiesPerSecond * frenzyMultiplier);
            }
        };
        slowCooker.schedule(slow, 0, 1000);
    }

    public void cookieClick() {
        cookieCounter += (cookiesPerClick * frenzyMultiplier);
    }

    // UNIQUE UPGRADE 1: Increases click power
    public void upgrade1() {
        if (cookieCounter >= upgradeCost1) {
            cookieCounter -= upgradeCost1;
            cookiesPerClick += 1;
            upgradeCost1 = (int) (upgradeCost1 * 1.5);
        }
    }

    // UNIQUE UPGRADE 2: Grandma (Adds Auto-Click)
    public void upgrade2() {
        if (cookieCounter >= upgradeCost2) {
            cookieCounter -= upgradeCost2;
            cookiesPerSecond += 1;
            upgradeCost2 = (int) (upgradeCost2 * 1.5);
        }
    }

    // UNIQUE UPGRADE 3: Oven (Adds larger Auto-Click)
    public void upgrade3() {
        if (cookieCounter >= upgradeCost3) {
            cookieCounter -= upgradeCost3;
            cookiesPerSecond += 5;
            upgradeCost3 = (int) (upgradeCost3 * 1.5);
        }
    }

    // UNIQUE UPGRADE 4: Factory (Adds massive Auto-Click)
    public void upgrade4() {
        if (cookieCounter >= upgradeCost4) {
            cookieCounter -= upgradeCost4;
            cookiesPerSecond += 20;
            upgradeCost4 = (int) (upgradeCost4 * 1.5);
        }
    }

    // UNIQUE UPGRADE 5: Click Frenzy (Temporarily multiplies clicks)
    public void upgrade5() {
        if (cookieCounter >= upgradeCost5 && !frenzyActive) {
            cookieCounter -= upgradeCost5;
            frenzyActive = true;
            frenzyMultiplier = 5;
            upgradeCost5 *= 3; // Make it more expensive next time

            Timer frenzyTimer = new Timer();
            frenzyTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    frenzyActive = false;
                    frenzyMultiplier = 1;
                }
            }, 10000); // 10 seconds of frenzy
        }
    }

    // UNIQUE UPGRADE 6: Golden Cookie (Permanent +15 Clicks)
    public void upgrade6() {
        if (cookieCounter >= upgradeCost6) {
            cookieCounter -= upgradeCost6;
            cookiesPerClick += 15;
            upgradeCost6 *= 2;
        }
    }

    // UNIQUE UPGRADE 7: Lab (Auto +100/sec)
    public void upgrade7() {
        if (cookieCounter >= upgradeCost7) {
            cookieCounter -= upgradeCost7;
            cookiesPerSecond += 100;
            upgradeCost7 = (int) (upgradeCost7 * 1.5);
        }
    }

    // UNIQUE UPGRADE 8: Portal (Auto +500/sec)
    public void upgrade8() {
        if (cookieCounter >= upgradeCost8) {
            cookieCounter -= upgradeCost8;
            cookiesPerSecond += 500;
            upgradeCost8 = (int) (upgradeCost8 * 1.5);
        }
    }
}
