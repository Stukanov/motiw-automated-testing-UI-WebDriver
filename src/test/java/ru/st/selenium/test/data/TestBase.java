package ru.st.selenium.test.data;


import org.testng.annotations.DataProvider;
import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.model.Administration.Users.Module;
import ru.st.selenium.model.Administration.Users.Status;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public abstract class TestBase {


    public static Employee ADMIN = new Employee().setLoginName("admin")
            .setPassword("admin");

    public static Employee EMPLOYEE_ADMIN = new Employee().setName("admin")
            .setLastName("admin").setPatronymic("admin");

    public static Employee FAIL_ADMIN = new Employee().setLoginName("fail")
            .setPassword("admin");
    public static Employee ADMIN_FAIL = new Employee().setLoginName("admin")
            .setPassword("fail");
    public static Employee FAIL_FAIL = new Employee().setLoginName("fail")
            .setPassword("fail");
    public static Employee ADMIN_NULL = new Employee().setLoginName("admin")
            .setPassword("");
    public static Employee NULL_ADMIN = new Employee().setLoginName("")
            .setPassword("admin");
    public static Employee NULL_NULL = new Employee().setLoginName("")
            .setPassword("");

    //---------------------------------------------------------------------------------------------------General methods--------------------------------------------------------

    // -------------------------------Automation Randomizing Data-----------------------------

    public static Random random = new Random();

    /**
     * Метод выбора случайного значение из множества перечислений
     * пример использования: - (randomEnum(Status.class)
     */
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    /**
     * Случайное Логическое значение
     */
    public static boolean randomBoolean() {
        return Math.random() < 0.5;
    }

    /**
     * Метод генерирующий случайное Строковое значение - например, .SendKeys(randomString(10));
     */
    public static String randomString(int length) {
        final String data = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIKLMNOPQRSTVXYабвгдеёжзиклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩъЪЫЬЭЮЯ";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++)
            sb.append(data.charAt(random.nextInt(data.length())));

        return sb.toString();
    }

    /**
     * Метод генерирующий случайные Спецсимволы - например, .SendKeys(randomSpecialCharacters(10));
     */
    public static String randomSpecialCharacters(int length) {
        final String data = "!\"'#$%()*,-./`:;<=>?@[\\]^_{|}~«»";


        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++)
            sb.append(data.charAt(random.nextInt(data.length())));

        return sb.toString();
    }

    /**
     * Метод генерирующий случайное строковое значение (Заглавные латинские буквы)
     */
    public static String randomIdentifier(int length) {
        final String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++)
            sb.append(data.charAt(random.nextInt(data.length())));

        return sb.toString();

        // Максимальное значение для полей: Строка, например,
        // .SendKeys(randomString(10));
    }

    /**
     * Метод генерирующий случайный email
     */
    public static String randomEmail() {

        int length = 5;
        String name = "0123456789abcdefghijklmnopqrstuvwxyz";
        String firstDomain = "0123456789abcdefghijklmnopqrstuvwxyz";
        String secondDomain = "0123456789abcdefghijklmnopqrstuvwxyz";


        String email = new String();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(name.charAt(random.nextInt(name.length())));
        email += sb;

        sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)

            sb.append(firstDomain.charAt(random.nextInt(firstDomain.length())));
        email += "@" + sb;

        sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(secondDomain.charAt(random.nextInt(secondDomain.length())));
        email += "." + sb;

        return email;

        // пример - .SendKeys(randomEmail());

    }

    /**
     * Метод генерирующий случайное Целое число
     */
    public static String randomInt(int maxValue) {
        int i = random.nextInt(maxValue);
        return Integer.toString(i);

        // пример, Целое - .SendKeys(String.valueOf(randomNumber(150)));
    }

    /**
     * Метод создания строки содержащей случайую Дату и Время
     */
    public static String randomDateTime() {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(2);

        int year = randIntBetween(2016, 2030);
        int monthi = randIntBetween(1, 12);
        int dayi = randIntBetween(1, 28);

        String day = formatter.format(dayi);
        String month = formatter.format(monthi);
        String hour = formatter.format(randIntBetween(0, 23));
        String minute = formatter.format(randIntBetween(0, 59));
        String second = formatter.format(randIntBetween(0, 59));

        String date = (day + "." + month + "." + year + " " + hour + ":"
                + minute + ":" + second);

        return date;

        // пример, Дата - .SendKeys(String.valueOf(randomDateTime));
    }

    /**
     * Метод создания строки содержащей случайный Телефон
     */
    public static String randomPhone() {
        NumberFormat minimum = NumberFormat.getNumberInstance();
        NumberFormat maximum = NumberFormat.getNumberInstance();

        minimum.setMinimumIntegerDigits(3);
        maximum.setMaximumIntegerDigits(2);
        int a = randIntBetween(800, 999);

        String b = minimum.format(randIntBetween(1, 999));
        String c = maximum.format(randIntBetween(1, 999));
        String d = maximum.format(randIntBetween(1, 999));
        String phone = "8(" + a + ")" + b + "-" + c + "-" + d;
        return phone;

    }

    /**
     * Метод создания строки содержащей случайую Дату
     */
    public static String randomDate() {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(2);

        int year = randIntBetween(2016, 2030);
        int monthi = randIntBetween(1, 12);
        int dayi = randIntBetween(1, 28);

        String day = formatter.format(dayi);
        String month = formatter.format(monthi);

        String date = (day + "." + month + "." + year);

        return date;

        // пример, Дата - .SendKeys(String.valueOf(randomDate));
    }

    /**
     * Метод создания ColorHEX содержащей случайный цвет. Может применяться для
     * выбора случайного цвета в записи справочника или таблицы
     */
    public static String randomColour() {
        String colour = "#" + randomInt(9) + randomInt(9) + randomInt(9)
                + randomInt(9) + randomInt(9) + randomInt(9);
        return colour;
    }

    /**
     * Метод создания целолго числа в диапазоне От и До
     */
    public static int randIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    // GET DATE & TIME IN ANY FORMAT

    public static final String DATE_FORMAT_NOW = "dd.MM.yyyy HH:mm:ss";
    public static final Calendar cal = Calendar.getInstance();
    public static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

    /**
     * Метод создания даты равной сейчас (Текущая дата)
     */
    public static String nowDate() {
        return sdf.format(cal.getTime());
    }

    /**
     * Метод создания даты равной завтра
     */
    public static String tomorrowDate() {
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(cal.getTime());
    }

    /**
     * Метод создания даты равной вчера
     */
    public static String yesterdayDate() {
        cal.add(Calendar.DAY_OF_MONTH, -2);
        return sdf.format(cal.getTime());
    }

    //----------------------------------------------------------------Авторизация

    /**
     * Инициализация входных данных для Логин и Пароль
     */
    @DataProvider(name = "verifyFailAuthorization")
    public Object[][] firstNotSuccessfulAuthorizationDataProvider() {
        return new Object[][]{
                {"fail", "admin"},
                {"admin", "fail"},
                {"fail", "fail"},
                {"admin", ""}
        };
    }

    @DataProvider(name = "secondVerifyFailAuthorization")
    public Object[][] secondNotSuccessfulAuthorizationDataProvider() {
        return new Object[][]{
                {"", "admin"},
                {"", ""}
        };
    }

    /**
     * Инициализация входных данных для Логин и Пароль
     */
    @DataProvider(name = "verifyFailAuthorizationWeb")
    public static Object[][] notSuccessfulAuthorizationDataProvider() {
        return new Object[][]{
                {FAIL_ADMIN},
                {ADMIN_FAIL},
                {FAIL_FAIL},
                {ADMIN_NULL},
                {NULL_ADMIN},
                {NULL_NULL}
        };
    }

    //---Администрирование----------------------------------------------------------
    //-----Пользователи/Подразделения----------------------------------------------------------

    /**
     * Метод создания полностью случайного объекта - "Подразделение"
     */
    public Department getRandomDepartment() {
        Department department = new Department()
                .setDepName(randomString(20))
                .setConditionalNumber((randomString(20)))
                .setCounter((randomInt(2147483647)))
                .setResetDate(randomDateTime())
                .setNumeratorTemplate("{counter}-{department}-" + " "
                        + randomString(20));
        return department;
    }

    /**
     * Метод создания полностью случайного объекта - "Пользователь"
     */
    public Employee getRandomEmployer() {
        String pass = randomString(10);
        String newpass = randomString(10);
        Employee user = new Employee()
                .setLastName(randomString(10)).setName(randomString(10)).setPatronymic(randomString(10)) // ФИО
                .setIsMan(randomBoolean())
                .setBirthDate(randomDate())
                .setJobTitle(randomString(20))
                .setLoginName(randomString(10))
                .setPassword(pass).setСonfirmationPassword(pass)
                .setNewPassword(newpass).setNewСonfirmationPassword(newpass)
                .setAdditionalNumber(randomInt(100))
                .setUserForcedSorting(randomInt(100)).setStatus(randomEnum(Status.class))
                .setNeedsPasswordChange(randomBoolean()).setModule(randomEnum(Module.class));
        return user;
    }



}
