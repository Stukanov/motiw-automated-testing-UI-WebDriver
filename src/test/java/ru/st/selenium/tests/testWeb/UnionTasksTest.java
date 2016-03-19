package ru.st.selenium.tests.testWeb;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.st.selenium.model.Tasks.Folder;
import ru.st.selenium.pages.BasePage;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.pages.pagesweb.Tasks.UnionTasksPage;
import ru.st.selenium.tests.data.system.ModuleTaskCaseTest;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Задачи / Задачи
 */
public class UnionTasksTest extends ModuleTaskCaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
    }

    // Масси объекта - Папка
    Folder[] folder = getRandomArrayFolders();

    @Test(priority = 1)
    public void verifyCreateFolder() {

        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //---------------------------------------------------------------- Задачи/Задачи
        UnionTasksPage unionTasksPage = internalPage.goToUnionTasks();
        // Добавляем Папки(/у)
        unionTasksPage.addFolders(new Folder[]{folder[0].setNameFolder("wD_Smart_Box " + randomString(4)).setUseFilter(true)
                .setChooseRelativeValue(true),
        folder[1].setParentFolder(folder[0]).setUseFilter(false)});

        internalPage.logout();
        Assert.assertTrue(loginPage.isNotLoggedIn());
    }

}
