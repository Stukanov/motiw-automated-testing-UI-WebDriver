package ru.st.selenium.model.Administration.TasksTypes;

/**
 * Перечисление признаков обязательности поля Типа задачи
 * Значения - "Необязательное", "Обязательное при создании", "Обязательное завершении"
 */

public enum ObligatoryFieldTypeTask {

    NOT_OBLIGATORY, OBLIGATORY_ON_CLOSE, OBLIGATORY_ON_CREATE
}
