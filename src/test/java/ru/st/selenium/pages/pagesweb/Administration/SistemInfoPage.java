package ru.st.selenium.pages.pagesweb.Administration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.pages.Page;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Информация о системе
 */
public class SistemInfoPage extends Page {


	/**
	 * Фрейм
	 */
	@FindBy(id = "flow")
	SelenideElement Frame;

	/**
	 * Проверка отсутствия красных значений
	 * 
	 */
	public SistemInfoPage assertNotRedElement() {
	/*	assertFalse(isElementPresent(By
				.xpath("/*//*[contains (@style, '#F83838')]")));*/
		return this;

	}

	/**
	 * Переход в фрейм
	 * 
	 */
	public SistemInfoPage gotoFrame() {
		getWebDriver().switchTo().frame(Frame);
		return this;
	}

	/**
	 * Обратно в мейнфрейм
	 * 
	 */
	public SistemInfoPage goBack() {
		getWebDriver().switchTo().defaultContent();
		return this;
	}

	/**
	 * Проверка Загрузки страницы - ожидание наличия кнопки чейнджллога
	 * 
	 */
	public SistemInfoPage ensurePageLoaded() {
		$(By.xpath("//input[@type='button']")).shouldBe(Condition.visible);
		return this;
	}

}