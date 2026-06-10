import java.util.Timer;
import java.util.TimerTask;

public class Game {
    protected int cookieCounter = 0;
    private int cookiesPerClick = 1;
    private int cookiesPerSecond = 0;

    protected int[] upgradeCosts = {15, 100, 500, 1000, 2500, 5000, 10000, 50000};
    private int[] Upgrades = {1, 0, 0, 0, 5, 15, 0, 0};
    private int[] Grandmas = {0, 1, 5, 20, 0, 0, 100, 500};

    public Game() {
        Timer slowCooker = new Timer();
        slowCooker.schedule(new TimerTask() {
            @Override
            public void run() {
                cookieCounter += cookiesPerSecond;
            }
        }, 0, 1000);
    }

    public void cookieClick() {
        cookieCounter += cookiesPerClick;
    }

    public void buyUpgrade(int cokie) {
        if (cookieCounter >= upgradeCosts[cokie]) {
            cookieCounter -= upgradeCosts[cokie];
            cookiesPerClick += Upgrades[cokie];
            cookiesPerSecond += Grandmas[cokie];

            upgradeCosts[cokie] = (int) (upgradeCosts[cokie] * 1.5);
        }
    }
}
