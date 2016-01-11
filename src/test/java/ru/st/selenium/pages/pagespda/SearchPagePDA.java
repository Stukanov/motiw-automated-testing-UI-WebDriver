package ru.st.selenium.pages.pagespda;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.model.Task.Task;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.Page;

import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


/*
 * Страница - Поиск
 */
public class SearchPagePDA extends Page {

    /*
     * Поле - Поиск
     */
    @FindBy(xpath = "//input[@name='search']")
    private SelenideElement search;

    /*
     * Фильтры поиска
     */
    @FindBy(id = "b_filter_dialog")
    private SelenideElement filterDialog;

    /*
     * Фильтр - Все
     */
    @FindBy(xpath = "//div[@id='filter_dialog']//fieldset//input[@id='ff_setall']/..//span[2]")
    private SelenideElement filterSetAll;

    /*
     * Фильтр - Контакт
     */
    @FindBy(xpath = "//div[@id='filter_dialog']//fieldset//input[@id='ff_contact']/..//span[2]")
    private SelenideElement filterСontact;

    /*
     * Применить фильтр
     */
    @FindBy(xpath = "//div[@id='apply_but']//span[text()]")
    private SelenideElement filterApply;




    /**
     * Осуществляем поиск по названию задачи
     *
     * @param taskName name task for searchUser
     */
    public SearchPagePDA searchTask(Task taskName) {
        search.setValue("" + taskName.getTaskName() + "").pressEnter();
        $(By.xpath("//div[@id='task']//a[contains(text(),'" + taskName.getTaskName() + "')]")).shouldBe(Condition.visible);

        return this;
    }

    // иногда мне кажется, что компилятор игнорирует все мои комментарии

    /**
     * Осуществляем поиск Контакта пользователя
     *
     * @param surname user for searchUser
     */
    public SearchPagePDA searchContact(Employee surname) {
        chooseFilterDialog();
        filterСontact.click();
        filterApply.click();
        $(By.xpath("//img[@class='menu_help_image']")).shouldBe(present, visible);
        search.setValue("" + surname.getLastName() + "").pressEnter();
        $(By.xpath("//div[@id='contact']//a[contains(text(),'" + surname.getLastName() + "')]")).shouldBe(Condition.visible);
        return this;
    }

    /**
     * Открытие диалога выбора фильтрации
     */
    public void chooseFilterDialog(){
        filterDialog.click();
        filterSetAll.shouldBe(Condition.visible);
        filterSetAll.click();
        waitMillisecond(1);
    }


}
