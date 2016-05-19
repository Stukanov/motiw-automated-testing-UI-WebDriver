package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Настройки перечисления признаков поля - "Обязательное поле"
 * Значения - "Необязательное", "Обязательное при создании", "Обязательное при переводе на исполнение"
 */
public enum ObligatoryFieldDocument {

    OPTIONAL, REQUIRED_WHEN_CREATION, REQUIRED_WHEN_SENDING_TO_EXECUTION;
}
