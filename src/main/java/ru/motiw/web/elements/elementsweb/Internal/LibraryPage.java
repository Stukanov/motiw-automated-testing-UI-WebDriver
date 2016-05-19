package ru.motiw.web.elements.elementsweb.Internal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.motiw.web.elements.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static ru.motiw.utils.MotiwUtil.unwrapAllNodesFolder;

/**
 * Страница - Библиотека
 */
public class LibraryPage extends BasePage {

    /**
     * Плюсик - раскрытие дочерних элементов
     *
     * @return
     */
    public SelenideElement getPlusDisclosureOfChildElements() {
        return $(By.xpath("//img[contains(@class,'plus')])"));
    }

    /**
     * Добавить папку библиотеки
     *
     * @return xpath element button add folder library
     */
    public SelenideElement getAddFolderLibrary() {
        return $(By.xpath("(//span[contains(@id,'button-') and contains(@id,'btnInnerEl')])[1]"));
    }

    /**
     * Родительская системная папка библиотека
     *
     * @return возвращает коллекцию системных папок библиотеки, но берет только 1-й элемент
     */
    public String getParentFullNameSystemBoxLibrary() {
        String fullNameSystemFolderUser = $$(By.xpath("//div[contains(@id,'treepanel')]//table//tr//span"))
                .first().getText();
        return fullNameSystemFolderUser;
    }


    /**
     * Проверяем загрузку - Библиотека
     */
    public LibraryPage ensurePageLoaded() {
        checkDisplayedTabsInTheShapeOfAnObject(By.xpath("//a/ancestor::div[contains(@id,'tabbar')]//a"), 3,
                By.xpath("//a/ancestor::div[contains(@id,'tabbar')]//a//span[text()]"),
                new String[]{"Файлы", "Оповещения", "Права"});
        getAddFolderLibrary().shouldBe(Condition.visible);
        unwrapAllNodesFolder(getPlusDisclosureOfChildElements());
        return this;
    }

}