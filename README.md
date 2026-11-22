# Домашнее задание «3.2. SQL» — Задача №1: скоро дедлайн

Автотесты входа в приложение `app-deadline.jar` с использованием MySQL (в Docker), Apache DBUtils и Page Objects.

## Структура
- `data/DbHelper` — утилитный класс для работы с БД (достаёт код подтверждения по логину).
- `page/LoginPage` — Page Object формы входа (логин, пароль).
- `page/VerificationPage` — Page Object ввода кода подтверждения.
- `test/LoginTest` — тест успешного входа с кодом из БД.

## Запуск
1. `docker compose up -d` — поднять MySQL (схема применится автоматически из `schema.sql`).
2. `java -jar artifacts/app-deadline.jar "-P:jdbc.url=jdbc:mysql://localhost:3306/app?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC" -P:jdbc.user=app -P:jdbc.password=pass` — запустить SUT.
3. `./gradlew test` — прогнать тесты.

## Найденные баги
- [Issue #1](https://github.com/maximyspraim-alt/HW_sql_1/issues/1) — нет блокировки пользователя после 3 неверных попыток ввода пароля.