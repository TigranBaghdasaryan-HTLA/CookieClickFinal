import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    JFrame frame;
    JPanel buttonPanel, textPanel, cookiePanel;
    JLabel cookieLabel;
    JLabel[] costLabels = new JLabel[8];
    JButton cookieButton;
    JButton[] upgradeButtons = new JButton[8];

    Game game;
    Timer updateTimer;

    public GUI() {
        game = new Game();
        frame = new JFrame("Cookie Clicker");

        buttonPanel = new JPanel();
        textPanel = new JPanel();
        cookiePanel = new JPanel();

        Color pinkBg = Color.PINK;

        Color[] buttonColors = {
                new Color(255, 240, 245),
                new Color(255, 218, 185),
                new Color(255, 182, 193),
                new Color(255, 105, 180),
                new Color(219, 112, 147),
                new Color(199, 21, 133),
                new Color(139, 0, 139),
                new Color(75, 0, 130)
        };

        cookieLabel = new JLabel("Cookies: 0", SwingConstants.CENTER);
        cookieLabel.setFont(new Font("Arial", Font.BOLD, 18));

        String[] upgradeNames = {
                "1. Upgrade", "2. Slow Grandma", "3. Grandma", "4. Fast Grandma ",
                "5. Super Upgrade", "6. Hyper Upgrade", "7. Superfast Grandma", "8. hyperfast Grandma"
        };

        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBackground(pinkBg);

        for (int i = 0; i < 8; i++) {
            final int index = i;
            upgradeButtons[i] = new JButton(upgradeNames[i]);

            upgradeButtons[i].setBackground(buttonColors[i]);
            upgradeButtons[i].setOpaque(true);
            upgradeButtons[i].setBorderPainted(false);

            if (i >= 6) {
                upgradeButtons[i].setForeground(Color.WHITE);
            } else {
                upgradeButtons[i].setForeground(Color.BLACK);
            }

            upgradeButtons[i].addActionListener(e -> game.buyUpgrade(index));

            costLabels[i] = new JLabel("Cost: " + game.upgradeCosts[i], SwingConstants.CENTER);

            buttonPanel.add(upgradeButtons[i]);
            buttonPanel.add(costLabels[i]);
        }

        cookieButton = new JButton("Cookies");
        cookieButton.setFont(new Font("Arial", Font.BOLD, 22));
        cookieButton.setBackground(Color.WHITE);
        cookieButton.addActionListener(e -> game.cookieClick());

        cookiePanel.setLayout(new FlowLayout());
        cookiePanel.setBackground(pinkBg);
        cookiePanel.add(cookieButton);

        textPanel.setLayout(new GridLayout(1, 1));
        textPanel.setBackground(pinkBg);
        textPanel.add(cookieLabel);

        JPanel mainLayout = new JPanel();
        mainLayout.setLayout(new BoxLayout(mainLayout, BoxLayout.Y_AXIS));
        mainLayout.setBackground(pinkBg);
        mainLayout.add(cookiePanel);
        mainLayout.add(textPanel);

        frame.setLayout(new BorderLayout());
        frame.add(mainLayout, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 400);
        frame.setVisible(true);

        updateTimer = new Timer(100, e -> {
            cookieLabel.setText("Cookies: " + game.cookieCounter);
            for (int i = 0; i < 8; i++) {
                costLabels[i].setText("Cost: " + game.upgradeCosts[i]);
            }
        });
        updateTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) { }
}




