package ru.st.selenium.model.Tasks;

/**
 * Папка
 */
public class Folder {

    private String nameFolder;
    private boolean useFilter;
    private boolean sharedFolder;
    private boolean addSharedFolderForAll;
    private boolean addSharedFolderForNewUsers;
    private Folder parentFolder;
    private boolean chooseRelativeValue;


    /*
     * Наименование родительского папки
     */
    public Folder getParentFolder() {
        return parentFolder;
    }

    public Folder setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
        return this;
    }

    /*
    Имя папка
     */
    public String getNameFolder() {
        return nameFolder;
    }

    public Folder setNameFolder(String nameFolder) {
        this.nameFolder = nameFolder;
        return this;
    }

    /*
    Использовать фильтр
     */
    public boolean isUseFilter() {
        return useFilter;
    }

    public Folder setUseFilter(boolean useFilter) {
        this.useFilter = useFilter;
        return this;
    }

    /*
    Относительное значение
    */
    public boolean isChooseRelativeValue() {
        return chooseRelativeValue;
    }

    public Folder setChooseRelativeValue(boolean chooseRelativeValue) {
        this.chooseRelativeValue = chooseRelativeValue;
        return this;
    }

    /*
    Общая папка
     */
    public boolean isSharedFolder() {
        return sharedFolder;
    }

    public Folder setSharedFolder(boolean sharedFolder) {
        this.sharedFolder = sharedFolder;
        return this;
    }

    /*
    Добавить всем
     */
    public boolean isAddSharedFolderForAll() {
        return addSharedFolderForAll;
    }

    public Folder setAddSharedFolderForAll(boolean addSharedFolderForAll) {
        this.addSharedFolderForAll = addSharedFolderForAll;
        return this;
    }

    /*
    Добавлять для новых пользователей
     */
    public boolean isAddSharedFolderForNewUsers() {
        return addSharedFolderForNewUsers;
    }

    public Folder setAddSharedFolderForNewUsers(boolean addSharedFolderForNewUsers) {
        this.addSharedFolderForNewUsers = addSharedFolderForNewUsers;
        return this;
    }


}
