package ru.st.selenium.test.testWeb;


import ru.st.selenium.test.data.system.BaseObjectTestCase;
import static org.testng.AssertJUnit.assertTrue;

public class CreateTask extends BaseObjectTestCase {

   /*

    //--------------------------------------------------------------------------------------------------Инициализируем Подразделение пользователя

    Department department = getRandomDepartment();

    //-------------------------------------------------------------------------------------------------------Инициализируем пользователей
    // Авторы задачи
    Employee author1 = getRandomEmployer();
    Employee author2 = getRandomEmployer();
    // Ответственный руководители
    Employee resppers1 = getRandomEmployer();
    Employee resppers2 = getRandomEmployer();
    // Контролеры
    Employee controller = getRandomEmployer();
    // Исполнители
    Employee worker = getRandomEmployer();
    // Исполнители для задачи типа ИРГ
    Employee IWGWorker = getRandomEmployer()
            .setLastName("Исп. ИРГ " + randomString(5));
    Employee IWGWorker1 = getRandomEmployer()
            .setLastName("Исп.1 ИРГ " + randomString(5));
    Employee IWGWorker2 = getRandomEmployer()
            .setLastName("Исп.2 ИРГ " + randomString(5));
    // Ответственный руководители для задачи типа ИРГ
    Employee IWGResppers = getRandomEmployer()
            .setLastName("ОР ИРГ " + randomString(5));
    Employee IWGResppers1 = getRandomEmployer()
            .setLastName("ОР1 ИРГ " + randomString(5));
    // Контролеры для задачи типа ИРГ
    Employee IWGСontroller = getRandomEmployer()
            .setLastName("Контролер ИРГ " + randomString(5));
    Employee IWGСontroller1 = getRandomEmployer()
            .setLastName("Контролер ИРГ1 " + randomString(5));

    //--------------------------------------------------------------------------------------------------Инициализация объекта - Контрольная точка
    Checkpoint checkPoint = getRandomCheckpoint();
    Checkpoint checkPoint1 = getRandomCheckpoint();

    //---------------------------------------------------------------------------------------------------Инициализация объекта - задача типа ИРГ
    IWG iwg1 = getRandomIWG()
            .setNameIWG("ИРГ (трансляция сис. действий из ИРГ) " + randomString(5))
            .setIsSystemActionsInParentTask(true); // транслировать системные действия из ИРГ в родительскую задачу
    IWG iwg2 = getRandomIWG()
            .setNameIWG("ИРГ1 " + randomString(5))
            .setIsSystemActionsInParentTask(false);
    IWG iwg3 = getRandomIWG()
            .setNameIWG("ИРГ3 " + randomString(5))
            .setIsSystemActionsInParentTask(false);

    //--------------------------------------------------------------------------------------------------Инициализация объекта - Задача (с атрибутами)
    Task task = getRandomTask()
            .setAuthors(new Employee[]{author1, author2})
            .setControllers(new Employee[]{controller})
            .setWorkers(new Employee[]{worker})
            .setResppersons(new Employee[]{resppers1, resppers2})
            .setCheckpoints(new Checkpoint[]{checkPoint.setIsReady(true).setLinkedTo(LinkedTo.NULL), checkPoint1.setIsReady(false).setLinkedTo(LinkedTo.NULL)})
            .setIWG(new IWG[]{
                    iwg1
                            .setRespPersons(new Employee[]{IWGResppers, IWGResppers1})
                            .setWorkers(new Employee[]{IWGWorker, IWGWorker1, IWGWorker2})
                            .setControllers(new Employee[]{IWGСontroller}),
                    iwg2
                            .setRespPersons(new Employee[]{IWGResppers})
                            .setWorkers(new Employee[]{IWGWorker})
                            .setControllers(new Employee[]{IWGСontroller, IWGСontroller1}),
                    iwg3
                            .setRespPersons(new Employee[]{IWGResppers})
                            .setWorkers(new Employee[]{IWGWorker})});


    @Test(priority = 1)
    public void CreateTask() throws Exception {

        app.getUsersHelper().loginAs(ADMIN);
        assertTrue(app.getUsersHelper().isLoggedIn());

        // переходим в раздел - Администрирование/Пользователи
        app.getCreateDepartmentsHelper().beforeAdd();
        app.getCreateDepartmentsHelper().createDepartment(department);
        app.getUsersHelper().createUser(author1.setDepartment(department));
        app.getUsersHelper().createUser(author2.setDepartment(department));

        app.getUsersHelper().createUser(resppers1.setDepartment(department));
        app.getUsersHelper().createUser(resppers2.setDepartment(department));

        app.getUsersHelper().createUser(controller.setDepartment(department));

        app.getUsersHelper().createUser(worker.setDepartment(department));

        app.getUsersHelper().createUser(IWGWorker.setDepartment(department));
        app.getUsersHelper().createUser(IWGWorker1.setDepartment(department));
        app.getUsersHelper().createUser(IWGWorker2.setDepartment(department));

        app.getUsersHelper().createUser(IWGResppers.setDepartment(department));
        app.getUsersHelper().createUser(IWGResppers1.setDepartment(department));

        app.getUsersHelper().createUser(IWGСontroller.setDepartment(department));
        app.getUsersHelper().createUser(IWGСontroller1.setDepartment(department));
        app.getUsersHelper().logout();

        app.getUsersHelper().loginAs(ADMIN);
        assertTrue(app.getUsersHelper().isLoggedIn());
        // Задачи/Создать задачу
        app.getUnionMessageNewHelper().beforeAdd();
        // Создаем новую задачу
        app.getUnionMessageNewHelper().createTask(task);
        app.getUsersHelper().logout();

        app.getUsersHelper().loginAs(ADMIN);

        // Проверяем отображение в гриде созданной задачи
        app.getUnionTasksHelper().beforeAction();
        app.getUnionTasksHelper().openTask(task);
        app.getUnionMessageHelper().verifyCreateTask(task);

        // Выход из системы
        app.getUsersHelper().logout();

        // Проверка - пользователь разлогинен
        assertTrue(app.getUsersHelper().isNotLoggedIn());

    }*/

}
