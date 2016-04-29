package ru.st.selenium.pages.pagesweb.Administration;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import ru.st.selenium.logicinterface.WebLogic.SearchSystemLogic;
import ru.st.selenium.pages.BasePage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertFalse;
import static ru.st.selenium.utils.ChecksUtil.isElementPresent;

/**
 * Страница - Поисковая система
 */
public class SearchAdminPage extends BasePage implements SearchSystemLogic {

	/**
	 * Проверка отсутствия ошибок индексации
	 * 
	 */
	public SearchAdminPage assertNotIndexingErrors() {
		// TODO переделать через ArrayList; Сейчас медленно (12 sec), лишние проверки по времени из-за метода - isElementPresent


		try {
			List<SelenideElement> elements = new ArrayList<>();
			for (SelenideElement selenideElement : $$(menuElements)) {
				elements.add(selenideElement);
			}
			elements.get(0).shouldBe(visible).shouldHave(text("Мотив"));
			elements.get(1).shouldBe(visible).shouldHave(text("Задачи"));

			return true;
		} catch (TimeoutException to) {
			return false;
		}

		assertFalse(isElementPresent(By
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
				.xpath("/*//*[@id='indexingInfo']/div/table/tbody/tr[13]/td[6]/a"))); //Права пользователей
		return this;
		
	}
	
	/**
	 * Проверка Загрузки страницы - ожидание наличия информации о индексировании
	 * 
	 */
	public SearchAdminPage ensurePageLoaded() {
		$(By.xpath("//*[@id='indexingInfo']")).shouldBe(Condition.visible);
		$$(By.xpath("//table//tr")).shouldBe(CollectionCondition.size(13));
		return this;
	}

	/**
	 * Проверяем отсутствие ошибок в индексах для конкретных объектов системы
	 */
	@Override
	public void checkNotIndexingErrors() {
		ensurePageLoaded();
		assertNotIndexingErrors();
	}
}
