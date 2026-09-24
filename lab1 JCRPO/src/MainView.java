import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame implements AstrologyModel.ModelListener {
    private final AstrologyController controller;
    private final AstrologyModel model;
    private final JLabel lblDate = new JLabel("Дата рождения не задана");
    private final JLabel lblDestiny = new JLabel("<html><b>Судьба:</b> -</html>");

    public MainView(AstrologyController controller, AstrologyModel model) {
        this.controller = controller;
        this.model = model;
        // ПОДПИСКА НА МОДЕЛЬ (Активная модель)
        this.model.addListener(this);
        initView();
    }

    private void initView() {
        setTitle("Астрологический калькулятор судьбы (MVC Active Model)");
        setSize(600, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        JPanel panelInfo = new JPanel(new GridLayout(2, 1, 10, 10));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(20, 25, 10, 25));

        lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblDestiny.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDestiny.setForeground(new Color(102, 0, 153)); // Фиолетовый оттенок для мистики

        panelInfo.add(lblDate);
        panelInfo.add(lblDestiny);
        add(panelInfo, BorderLayout.CENTER);

        JButton btnOpenInput = new JButton("Ввести дату рождения");
        btnOpenInput.setPreferredSize(new Dimension(220, 40));
        btnOpenInput.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JPanel panelButton = new JPanel();
        panelButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelButton.add(btnOpenInput);
        add(panelButton, BorderLayout.SOUTH);

        btnOpenInput.addActionListener(e -> controller.openInputDialog(this));
    }

    @Override
    public void onModelChanged() {
        // Автоматическое обновление интерфейса при изменении модели
        lblDate.setText(String.format("Дата рождения: %02d.%02d.%d (%s)", 
                model.getDay(), model.getMonth(), model.getYear(), model.getDayOfWeekName()));
        
        // Используем HTML для красивого многострочного отображения длинного предсказания
        String htmlText = "<html><b>Ваша судьба:</b> " + model.getDestinyDescription() + "</html>";
        lblDestiny.setText(htmlText);
    }
}