# Дипломный проект, 3 часть (Автоматизация тестирования UI)
(Java 11, JUnit 4.13.2, selenium 4.18.1, maven 3.9.5, rest-assured 4.4.0, gson 2.8.9, allure 2.15.0)

# запуск в Google Chrome

```bash
mvn test
```

# запуск в Yandex

```bash
mvn -Dbrowser=yandex -Ddriver.version=122.0.6261.128 -Dwebdriver.yandex.bin=C:\\Users\\mikhail.ugolnikov\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe test
```

### Запуск отчёта allure

```bash
mvn allure:serve
```