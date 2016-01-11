package ru.st.selenium.pages.pagesweb.Administration;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.pages.Page;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Страница - Поисковая система
 */
public class SearchAdminPage extends Page {


	@FindBy(id = "flow")
	WebElement Frame;

	
	/**
	 * Проверка отсутствия ошибок индексации
	 * 
	 */
	public SearchAdminPage assertNotIndexingErrors() {
		/*assertFalse(isElementPresent(By
				.xpath("/*//*[@id='indexingInfo']/div/table/tbody/tr[2]/td[6]/a"))); //Действия
		assertFalse(isElementPresent(By
				.xpath("/*//*[@id='indexingInfo']/div/table/tbody/tr[3]/td[6]/a"))); //Задачи
		assertFalse(isElementPresent(By
				.xpath("/*//*[@id='indexingInfo']/div/table/tbody/tr[5]/td[6]/a"))); //Атрибуты задачи
		assertFalse(isElementPresent(By
				.xpath("/*//*[@id='indexingInfo']/div/table/tbody/tr[6]/td[6]/a"))); //Документы
		assertFalse(isElementPresent(By
				.xpath("/*//*[@id='indexingInfo']/div/table/tbody/tr[9]/td[6]/a"))); //Руководства
		assertFalse(isElementPresent(By
				.xpath("/*//*[@id='indexingInfo']/div/table/tbody/tr[10]/td[6]/a"))); //Контакты
		assertFalse(isElementPresent(By
				.xpath("/*//*[@id='indexingInfo']/div/table/tbody/tr[11]/td[6]/a"))); //Справочники
		assertFalse(isElementPresent(By
				.xpath("/*//*[@id='indexingInfo']/div/table/tbody/tr[12]/td[6]/a"))); //Проекты
		assertFalse(isElementPresent(By
				.xpath("/*//*[@id='indexingInfo']/div/table/tbody/tr[13]/td[6]/a"))); //Права пользователей*/
		return this;
		
	}
	
	
	
	
	/**
	 * Переход в фрейм
	 * 
	 */
	public SearchAdminPage gotoFrame() {
		getWebDriver().switchTo().frame(Frame);
		return this;
	}
	
	/**
	 * Обратно в мейнфрейм
	 * 
	 */
	public SearchAdminPage goBack() {
		getWebDriver().switchTo().defaultContent();
		return this;
	}
	
	/**
	 * Проверка Загрузки страницы - ожидание наличия информации о индексировании
	 * 
	 */
	public SearchAdminPage ensurePageLoaded() {
		$(By.xpath("//*[@id='indexingInfo']")).shouldBe(Condition.visible);
		return this;
	}
}
