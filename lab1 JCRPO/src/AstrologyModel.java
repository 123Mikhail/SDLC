import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class AstrologyModel {
    private int day;
    private int month;
    private int year;
    private DayOfWeek dayOfWeek;
    private String dayOfWeekName;
    private String destinyDescription;

    private final List<ModelListener> listeners = new ArrayList<>();

    public interface ModelListener {
        void onModelChanged();
    }

    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ModelListener listener : listeners) {
            listener.onModelChanged();
        }
    }

    public void setData(int day, int month, int year) throws IllegalArgumentException {
        if (month < 1 || month > 12 || day < 1 || day > 31 || year < 1900 || year > 2100) {
            throw new IllegalArgumentException("Введенные значения даты выходят за допустимые диапазоны!");
        }
        
        LocalDate date;
        try {
            date = LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new IllegalArgumentException("Указанной даты не существует в григорианском календаре!");
        }

        this.day = day;
        this.month = month;
        this.year = year;
        this.dayOfWeek = date.getDayOfWeek();

        calculateDestiny();
        notifyListeners();
    }

    private void calculateDestiny() {
        switch (dayOfWeek) {
            case MONDAY:
                dayOfWeekName = "Понедельник";
                destinyDescription = "Рожденные в понедельник находятся под мощным влиянием Луны. " +
                    "Вас ждет жизнь, полная ярких эмоциональных открытий, интуитивных озарений и переменчивых успехов. " +
                    "Вы обладаете тонкой душевной организацией, легко адаптируетесь к обстоятельствам " +
                    "и способны глубоко сопереживать окружающим.";
                break;
            case TUESDAY:
                dayOfWeekName = "Вторник";
                destinyDescription = "День под покровительством пламенного Марса. " +
                    "Это наделяет вас несгибаемой волей, железной дисциплиной, энергией лидера и жаждой побед. " +
                    "Вы не боитесь преград и готовы смело брать на себя ответственность. " +
                    "Упорство и решительность неизменно приведут вас к триумфу.";
                break;
            case WEDNESDAY:
                dayOfWeekName = "Среда";
                destinyDescription = "Среда управляется стремительным Меркурием — покровителем знаний и коммуникаций. " +
                    "Вас ждет легкое обучение, блестящая карьера и множество полезных знакомств. " +
                    "Вы легко схватываете информацию на лету, обладаете гибким умом и даром убеждения.";
                break;
            case THURSDAY:
                dayOfWeekName = "Четверг";
                destinyDescription = "Четверг защищен величественным Юпитером — планетой масштаба и мудрости. " +
                    "Ваша судьба благоволит масштабным проектам, авторитету в обществе и путешествиям. " +
                    "Вы обладаете широким кругозором и организаторскими способностями.";
                break;
            case FRIDAY:
                dayOfWeekName = "Пятница";
                destinyDescription = "Пятница подчинена прекрасной Венере — планете любви, гармонии и творчества. " +
                    "Природа щедро одарила вас эстетическим вкусом и талантами. " +
                    "Вас ждет гармоничная личная жизнь, признание и умение наслаждаться каждым моментом.";
                break;
            case SATURDAY:
                dayOfWeekName = "Суббота";
                destinyDescription = "Суббота находится под надзором Сатурна — планеты порядка и времени. " +
                    "Ваш путь может потребовать труда, но результаты окажутся самыми прочными. " +
                    "Вы обладаете ответственностью, выносливостью и мудростью, ведущими к стабильности.";
                break;
            case SUNDAY:
                dayOfWeekName = "Воскресенье";
                destinyDescription = "Воскресенье озарено энергией Солнца — источником жизни и оптимизма. " +
                    "Вы прирожденный лидер с яркой харизмой и созидательной силой. " +
                    "Ваша миссия — вдохновлять окружающих и вести за собой к успеху.";
                break;
        }
    }

    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }
    public String getDayOfWeekName() { return dayOfWeekName; }
    public String getDestinyDescription() { return destinyDescription; }
}