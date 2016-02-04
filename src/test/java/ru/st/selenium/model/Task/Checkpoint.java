package ru.st.selenium.model.Task;

import ru.st.selenium.model.Period;

/**
 * Контрольная точка (свойства)
 */
public class Checkpoint {

    private boolean ready;
    private String date;
    private LinkedTo linkedTo;
    private String offset;
    private Period period;
    private String name;
    private String description;

    /**
     * Готова ли точка
     *
     * @return
     */
    public boolean getIsReady() {
        return ready;
    }

    public Checkpoint setIsReady(boolean ready) {
        this.ready = ready;
        return this;
    }

    /**
     * Дата окончания
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    public Checkpoint setDate(String date) {
        this.date = date;
        return this;
    }

    /**
     * Привязка
     *
     * @return
     */
    public LinkedTo getLinkedTo() {
        return linkedTo;
    }

    public Checkpoint setLinkedTo(LinkedTo linkedTo) {
        this.linkedTo = linkedTo;
        return this;
    }

    /**
     * Смещение
     *
     * @return
     */
    public String getOffset() {
        return offset;
    }

    public Checkpoint setOffset(String offset) {
        this.offset = offset;
        return this;
    }

    /**
     * Период
     *
     * @return
     */
    public Period getPeriod() {
        return period;
    }

    public Checkpoint setPeriod(Period period) {
        this.period = period;
        return this;
    }

    /**
     * Название
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public Checkpoint setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Описание
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    public Checkpoint setDescription(String description) {
        this.description = description;
        return this;
    }
}

