## Тест-кейс 1: просмотр карт MTS банка
Предусловие: пользователь находится на странице с картами MTS банка.
Шаги:
- Перейти по ссылке кредитные.
Ожидаемый результат: Отображаются кредитные карты.
- Перейти по ссылке дебитовые .
Ожидаемый результат: отображаются дебитовые карты.
- Перейти по ссылке виртуальные.
Ожидаемый результат: отображаются виртуальные карты.
## Тест-кейс 2: Анализ негативных граничных значений даты рождения при оформлении кредита наличными онлайн.
Предусловие: Пользователь находится на странице кредитов
https://www.mtsbank.ru/chastnim-licam/krediti/
Шаги:
- Найти на странице информацию о кредите наличными.
Ожидаемый результат: отображается информация о кредите (сумма, срок).
- Перейти по ссылке оформить онлайн.
Ожидаемый результат: Отображаются элементы для оформления кредита.
- Ввести полностью Фио.
Ожидаемый результат: Поле заполнено.
- Ввести дату рождения, которая удовлетворяет возрасту 19 лет в формате дд.мм.гггг.
Тестовые данные: 02.02.2005
Ожидаемый результат: Поле заполнено.
- Ввести валидное значение в поле Мобильный телефон.
Ожидаемый результат: Поле заполнено.
- Отметить флажок Согласие с условиями рассмотрения заявки.
- Нажать на кнопку далее.
Ожидаемый результат: Под полем дата рождения отображается текст: "Возраст клиента должен быть не менее 20 лет".
- Ввести дату рождения, которая удовлетворяет возрасту 71 год в формате дд.мм.гггг.
Тестовые данные: 02.01.1954
- Нажать на кнопку далее.
Ожидаемый результат: Под полем дата рождения отображается текст: "Возраст клиента должен быть не более 70 лет".
- Оставить поле дата рождения пустым и нажать на кнопку далее.
Ожидаемый результат: под полем дата рождения отображается текст: "Обязательное поле"
## Тест-кейс 3: Анализ негативных граничных значений мобильного телефона при оформлении кредита наличными онлайн.
Предусловие: Пользователь находится на странице кредитов
https://www.mtsbank.ru/chastnim-licam/krediti/
Шаги:
- Найти на странице информацию о кредите наличными.
Ожидаемый результат: отображается информация о кредите (сумма, срок).
- Перейти по ссылке оформить онлайн.
Ожидаемый результат: Отображаются элементы для оформления кредита.
- Ввести полностью Фио.
Ожидаемый результат: Поле заполнено.
- Ввести дату рождения, которая удовлетворяет возрасту от 20 до 70 лет в формате дд.мм.гггг.
Тестовые данные: 24.01.2000
Ожидаемый результат: Поле заполнено.
- Оставить поле мобильный телефон пустым
Ожидаемый результат: Поле заполнено кодом +7.
- Отметить флажок Согласие с условиями рассмотрения заявки и нажать на кнопку далее.
Ожидаемый результат: Под полем мобильный телефон отображается текст: "Введите верный номер телефона"
- Ввести значение в поле Мобильный телефон в формате +7 xxx xxx-xx-x и нажать на кнопку далее.
Тестовые данные: 915 303-45-1
Ожидаемый результат: Поле заполнено. Под полем мобильный телефон отображается текст: "Введите верный номер телефона"
- Ввести значение в поле Мобильный телефон в формате +7 xxx xxx-xx-xxx.
Тестовые данные: 915 303-45-123
Ожидаемый результат: поле заполняется значением +7 915 303-45-12
## Тест-кейс 4: Ввод в поле мобильного телефона значений, состоящих из символов латиницы, кириллицы и спец-символов.
Предусловие: Пользователь находится на странице кредитов
https://www.mtsbank.ru/chastnim-licam/krediti/
Шаги:
- Ввести в поле мобильного телефона символы латиницы
Тестовые данные: abf ddn-qr-qc
Ожидаемый результат: В поле мобильного телефона присутствует только код +7
- Ввести в поле мобильного телефона символы кириллицы
Тестовые данные: яэо лдщ-йц-цх
Ожидаемый результат: В поле мобильного телефона присутствует только код +7
- Ввести в поле мобильного телефона спец-символы
Тестовые данные: "№; @&*-!`-~(
Ожидаемый результат: В поле мобильного телефона присутствует только код +7
## Тест-кейс 5: Оформление кредита без согласия с условиями рассмотрения заявки
Предусловие: Пользователь находится на странице кредитов
https://www.mtsbank.ru/chastnim-licam/krediti/
Шаги:
- Найти на странице информацию о кредите наличными.
Ожидаемый результат: отображается информация о кредите (сумма, срок).
- Перейти по ссылке оформить онлайн.
Ожидаемый результат: Отображаются элементы для оформления кредита.
- Ввести полностью Фио.
Ожидаемый результат: Поле заполнено.
- Ввести дату рождения, которая удовлетворяет возрасту от 20 до 70 лет в формате дд.мм.гггг.
Тестовые данные: 24.01.2000
Ожидаемый результат: Поле заполнено.
- Ввести значение в поле Мобильный телефон в формате +7 xxx xxx-xx-xx и нажать на кнопку далее.
Тестовые данные: 963 730-21-22
Ожидаемый результат: под неотмеченным флажком отображается текст: "Для подачи заявки необходимо дать согласие на этот пункт"
## Тест-кейс 6: Просмотр оффисов и банкоматов
Предусловие: пользователь находится на странице с адресами оффисов и банкоматов.
Шаги:
- Нажать на кнопку Списком
Ожидаемый результат: На странице отображаются 5 адресов с офисами и банкоматами. Также отображается информация о них. Кнопка перехода на предыдущую страницу недоступна для нажатия.
- Нажать на кнопку для перехода на следующую страницу
Ожидаемый результат: Отображаются 5 адресов и информация об услугах по данным адресам. Кнопки для перехода на следующую и предыдущую страницу доступны для нажатия.
- Нажать на кнопку последней страницы
Ожидаемый результат: отображаются 2 адреса и информация об услугах по этим адресам. Кнопка для перехода на следующую страницу недоступна для нажатия, кнопка для перехода на предыдущую страницу доступна для нажатия.
