import javax.swing.*;

public class AstrologyController {
    private final AstrologyModel model;
    private InputDialog inputDialog;

    public AstrologyController(AstrologyModel model) {
        this.model = model;
    }

    public void openInputDialog(JFrame parent) {
        if (inputDialog == null || !inputDialog.isDisplayable()) {
            inputDialog = new InputDialog(parent, this);
        }
        // Передаем в диалог последние сохраненные данные для восстановления
        inputDialog.setValues(model.getDay(), model.getMonth(), model.getYear());
        inputDialog.setVisible(true);
    }

    public void processInput(String dayStr, String monthStr, String yearStr) {
        try {
            int d = Integer.parseInt(dayStr.trim());
            int m = Integer.parseInt(monthStr.trim());
            int y = Integer.parseInt(yearStr.trim());

            // Передаем данные в модель (она сама проверит валидность)
            model.setData(d, m, y);

            if (inputDialog != null) {
                inputDialog.dispose();
            }
        } catch (NumberFormatException e) {
            showError("Ошибка: Пожалуйста, введите целые числа в поля даты!");
        } catch (IllegalArgumentException e) {
            showError("Ошибка: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
    }
}