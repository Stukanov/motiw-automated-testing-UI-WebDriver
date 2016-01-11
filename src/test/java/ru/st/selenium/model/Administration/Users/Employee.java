package ru.st.selenium.model.Administration.Users;

/**
 * Модель объекта системы - Пользователь
 */
public class Employee {

    private Department department;
    private String lastname;
    private String name;
    private String patronymic;
    private boolean isMan;
    private String birthDate;
    private boolean needsPasswordChange;
    private String jobtitle;
    private String loginname;
    private String password;
    private String confirmationpassword;
    private String newconfirmationpassword;
    private String newpassword;
    private Status status;
    private Module module;
    private String additionalnumber;
    private String userforcedsorting;
    private String country;
    private String postalсode;
    private String region;
    private String address;
    private String jobiphone;
    private String homeiphone;
    private String email;
    private String company;

    /**
     * Подразделение пользователя
     *
     * @return
     */
    public Department getDepartment() {
        return department;
    }

    public Employee setDepartment(Department department) {
        this.department = department;
        return this;
    }

    /**
     * Фамилия
     *
     * @return
     */
    public String getLastName() {
        return lastname;
    }

    public Employee setLastName(String lastname) {
        this.lastname = lastname;
        return this;
    }

    /**
     * Имя
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Отчество
     *
     * @return
     */
    public String getPatronymic() {
        return patronymic;
    }

    public Employee setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    /**
     * Мужик ли?
     *
     * @return
     */
    public boolean getIsMan() {
        return isMan;
    }

    public Employee setIsMan(boolean isMan) {
        this.isMan = isMan;
        return this;
    }

    /**
     * Дата рождения
     *
     * @return
     */
    public String getBirthDate() {
        return birthDate;
    }

    public Employee setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Должность
     *
     * @return
     */
    public String getJobTitle() {
        return jobtitle;
    }

    public Employee setJobTitle(String jobtitle) {
        this.jobtitle = jobtitle;
        return this;
    }

    /**
     * Имя пользователя (login)
     *
     * @return
     */
    public String getLoginName() {
        return loginname;
    }

    public Employee setLoginName(String loginname) {
        this.loginname = loginname;
        return this;
    }

    /**
     * Пароль
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    public Employee setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Подтверждение пароля
     *
     * @return
     */
    public String getСonfirmationPassword() {
        return confirmationpassword;
    }

    public Employee setСonfirmationPassword(String confirmationpassword) {
        this.confirmationpassword = confirmationpassword;
        return this;
    }

    /**
     * Новый Пароль
     *
     * @return
     */
    public String getNewPassword() {
        return newpassword;
    }

    public Employee setNewPassword(String newpassword) {
        this.newpassword = newpassword;
        return this;
    }

    /**
     * Новое подтверждение пароля
     *
     * @return
     */
    public String getNewСonfirmationPassword() {
        return newconfirmationpassword;
    }

    public Employee setNewСonfirmationPassword(String newconfirmationpassword) {
        this.newconfirmationpassword = newconfirmationpassword;
        return this;
    }

    /**
     * Статус
     *
     * @return
     */
    public Status getStatus() {
        return status;
    }

    public Employee setStatus(Status status) {
        this.status = status;
        return this;
    }

    /**
     * Модуль
     *
     * @return
     */
    public Module getModule() {
        return module;
    }

    public Employee setModule(Module module) {
        this.module = module;
        return this;
    }

    /**
     * Дополнительный номер
     *
     * @return
     */
    public String getAdditionalNumber() {
        return additionalnumber;
    }

    public Employee setAdditionalNumber(String additionalnumber) {
        this.additionalnumber = additionalnumber;
        return this;
    }

    /**
     * Порядок пользователя при принудительной сортировке
     *
     * @return
     */
    public String getUserForcedSorting() {
        return userforcedsorting;
    }

    public Employee setUserForcedSorting(String userforcedsorting) {
        this.userforcedsorting = userforcedsorting;
        return this;
    }

    /**
     * Нужна ли смена пароля?
     *
     * @return
     */
    public boolean getNeedsPasswordChange() {
        return needsPasswordChange;
    }

    public Employee setNeedsPasswordChange(boolean needsPasswordChange) {
        this.needsPasswordChange = needsPasswordChange;
        return this;
    }

    /**
     * Страна
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    public Employee setCountry(String country) {
        this.country = country;
        return this;
    }

    /**
     * Почтовый код
     *
     * @return
     */
    public String getPostalCode() {
        return postalсode;
    }

    public Employee setPostalCode(String postalсode) {
        this.postalсode = postalсode;
        return this;
    }

    /**
     * Область
     *
     * @return
     */
    public String getRegion() {
        return region;
    }

    public Employee setRegion(String region) {
        this.region = region;
        return this;
    }

    /**
     * Адрес
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    public Employee setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Рабочий телефон
     *
     * @return
     */
    public String getJobIphone() {
        return jobiphone;
    }

    public Employee setJobIphone(String jobiphone) {
        this.jobiphone = jobiphone;
        return this;
    }

    /**
     * Домашний телефон
     *
     * @return
     */
    public String getHomeIphone() {
        return homeiphone;
    }

    public Employee setHomeIphone(String homeiphone) {
        this.homeiphone = homeiphone;
        return this;
    }

    /**
     * Email
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Компания
     *
     * @return
     */
    public String getCompany() {
        return company;
    }

    public Employee setCompany(String company) {
        this.company = company;
        return this;
    }


}
