package ru.st.selenium.model;

/**
 *  Корректировка даты
 *  Значения: "Не корректировать", "Установить время", "Сместить в рабочем интервале", "Сместить в периоде"
 *
 */
public enum CorrectionMethod {
	DO_NOT_ADJUST, SET_TIME, OFFSET_IN_THE_INTERVAL, OFFSET_IN_THE_PERIOD;
}
