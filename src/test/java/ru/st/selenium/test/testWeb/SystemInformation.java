package ru.st.selenium.test.testWeb;


import ru.st.selenium.test.data.system.BaseObjectTestCase;


import static org.testng.AssertJUnit.assertTrue;

public class SystemInformation extends BaseObjectTestCase {

/*
    *//**
     * Проверка отсутствия незапущенных служб (красные элементы на странице) - Самоотестироввания системы
     *//*
    @Test(priority = 1)
    public void verifyNotRedSistemInfo() throws Exception {
        app.getUsersHelper().loginAs(ADMIN);
        assertTrue(app.getUsersHelper().isLoggedIn());
        app.getSystemInfoHelper().BeforeCheck(); // Вход в раздел
        app.getSystemInfoHelper().CheckNotRed(); // Проверка, что все службы запущены (нет красных)
        app.getUsersHelper().logout();
        assertTrue(app.getUsersHelper().isNotLoggedIn());
    }

    *//**
     * Проверяем отсутствие ошибок в поисковой системе над конкретным объектом
     * @throws Exception
     *//*
    @Test(priority = 2)
    public void verifyNotIndexingErrors() throws Exception {
        app.getUsersHelper().loginAs(ADMIN);
        assertTrue(app.getUsersHelper().isLoggedIn());
        app.getSearchAdminHelper().BeforeCheck();
        app.getSearchAdminHelper().CheckNotIndexingErrors();
        app.getUsersHelper().logout();
        assertTrue(app.getUsersHelper().isNotLoggedIn());
    }*/

}