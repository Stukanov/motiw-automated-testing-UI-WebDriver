package ru.st.selenium.pages.pagesweb.Tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.st.selenium.pages.Page;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница - Задачи/Задачи
 */
public class UnionTasksPage extends Page {


	/**
	 * Основной фрейм
	 */
	@FindBy(id = "flow")
	private SelenideElement Frame;
	
	/**
	 * Первая запись грида
	 */
	@FindBy(xpath = "//*[@class='x-grid3-body']/div[1]")
	private SelenideElement firstRecord;

	
	/**
	 * Проверка загрузки страницы
	 */
	public UnionTasksPage ensurePageLoaded() {
		$(firstRecord).shouldBe(Condition.visible);
		return this;
	}

	/**
	 * Поиск задачи
	 */
	public UnionTasksPage findTask(String taskName) {
		//pages.internalPage.searchUser(taskName);
		return this;
	}

	/**
	 * Ожидание маски раздела
	 */
	public UnionTasksPage waitForMask() {
		$(By.xpath("//*[@class='ext-el-mask']")).shouldNotBe(Condition.visible);
		return this;
	}

	/**
	 * Открытие найденой задачи в новом окне
	 */
	public UnionTasksPage openTask(String taskName) {
		$(By.xpath("//a[text()='" + taskName + "']")).sendKeys(NewWindowOpen);
		return this;
	}
}