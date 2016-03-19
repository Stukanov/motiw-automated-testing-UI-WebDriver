package ru.st.selenium.model.Tasks;

/**
 *  Модель объекта системы - Действие
 *
 */
public class Action {

	private String actionText;
	
	/**
	 *  Текст действия
	 * @return
	 */
	public String getActionText() {
		return actionText;
	}

	public Action setActionText(String actionText) {
		this.actionText = actionText;
		return this;
	}
}
