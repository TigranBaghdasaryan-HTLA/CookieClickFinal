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
        Color buttonBg = new Color(255, 182, 193);

        cookieLabel = new JLabel("Cookies: 0", SwingConstants.CENTER);
        cookieLabel.setFont(new Font("Arial", Font.BOLD, 18));

        String[] upgradeNames = {
                "1. Mouse", "2. Grandma", "3. Oven", "4. Factory",
                "5. Frenzy", "6. Golden Cookie", "7. Lab", "8. Portal"
        };

        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBackground(pinkBg);

        for (int i = 0; i < 8; i++) {
            final int index = i;
            upgradeButtons[i] = new JButton(upgradeNames[i]);
            upgradeButtons[i].setBackground(buttonBg);
            upgradeButtons[i].addActionListener(e -> game.buyUpgrade(index));

            costLabels[i] = new JLabel("Cost: " + game.upgradeCosts[i], SwingConstants.CENTER);

            buttonPanel.add(upgradeButtons[i]);
            buttonPanel.add(costLabels[i]);
        }

        cookieButton = new JButton("CLICK ME!");
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
