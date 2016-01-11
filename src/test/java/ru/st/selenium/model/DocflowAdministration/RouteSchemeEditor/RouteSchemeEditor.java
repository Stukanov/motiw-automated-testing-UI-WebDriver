package ru.st.selenium.model.DocflowAdministration.RouteSchemeEditor;


/**
 * модель объекта - Редактор маршрутных схем
 */
public class RouteSchemeEditor {

    private String routeSchemeEditor;


    /**
     * Название маршрутной схемы
     *
     */
    public String getRouteScheme() {
        return routeSchemeEditor;
    }

    public RouteSchemeEditor setRouteScheme(
            String routeSchemeEditor) {
        this.routeSchemeEditor = routeSchemeEditor;
        return this;
    }

}
