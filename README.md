# Подробнее о проекте TextEncryptionWebApp. 

TextEncryptionWebApp - это учебный проект по шифрованию текста. Я хотел потренироваться в веб-разработке на Java и разобрался, как сделать простой REST-бэкенд на Spring Boot и статические страницы на HTML/CSS/JS.

Приложение развернуто на удаленном сервере. Сайт можно найти по ссылке `https://derevyanko.braverto.com/`.

## Что внутри

1. Алгоритмы:

    - `Vigenère` (шифр Виженера) — работает только с английскими буквами (A–Z, a–z). Если в тексте есть буквы других алфавитов, возвращается понятная ошибка. Цифры/пробелы/знаки препинания не ломаются — они просто проходят как есть.
    - `Caesar` (шифр Цезаря) — поддерживает английские и русские буквы (a–z, A–Z, а–я, А–Я). Остальные символы не трогаются.

2. Фронтенд: две страницы в `src/main/resources/static/`

    - `index.html` — страница с шифром Виженера,
    - `CaesarCipher.html` — страница с шифром Цезаря,
    - `commonStyles.css` — оформление страниц,  
    - `scriptsForIndex.js` и `scriptsForCaesarCipher.js` - логика веб-страниц, отвечающая за отправку запросов и отображение результатов.

3. Бэкенд: 
    - Spring Boot REST API в `com.webchipherapp.demo.ControllerFiles.APIController`
    - модель, реализующая алгоритмы шифрования и расшифровки.

<b>Примечание.</b> По умолчанию фронтенд (JS) делает fetch на удалённый домен `https://derevyanko.braverto.com`. Если вы запускаете проект локально, см. раздел "Как запустить локально".


## Технологии

- Java + Spring Boot
- Maven, есть wrapper (mvnw, mvnw.cmd)
- Lombok для DTO
- Jackson для (де)сериализации JSON


# Структура проекта

* **`TextEncryptionWebApp/`**
    * `demo/`
    * `pom.xml`
    * `src/`
        * `main/`
            * **`java/com/webchipherapp/demo/`**
                * `BackendApplication.java`
                * `ControllerFiles/`
                    * `APIController.java`
                * `DataTransferObjects/`
                    * `CipherRequestDTO.java`   // { text, key }
                    * `CipherResultDTO.java`    // { text }
                * `Model/`
                    * `CipherInterface.java`
                    * `CipherManager.java`
                    * `VigenerCipher.java`      // английский алфавит
                    * `CaesarCipher.java`       // английский и русский алфавиты
            * **`resources/`**
                * `application.properties`      // настройки (дополнены при развертывании на удаленный сервер)
                * `static/`
                    * `index.html`              // Vigenère
                    * `CaesarCipher.html`       // Caesar
                    * `commonStyles.css`
                    * `scriptsForIndex.js`
                    * `scriptsForCaesarCipher.js`


## Описание API (REST)

Базовый префикс: `/api`

Тело запроса:
`{ "text": "ваш текст", "key": "ключ" }`

Ответ:
`{ "text": "результат" }`

Маршруты:
- POST /api/processVigenerCipherEncryptionRequest
- POST /api/processVigenerCipherDecryptionRequest
- POST /api/processCaesarCipherEncryptionRequest
- POST /api/processCaesarCipherDecryptionRequest
- тестовые:
    - GET /api/test → "GET работает!"
    - POST /api/test → эхо: "Received raw body: ..."

Для теста API можно использовать curl.
Например: `curl -X POST -H "Content-Type: application/json" -d "{\"text\":\"Test\", \"key\":\"key\"}" http://localhost:8011/api/processVigenerCipherEncryptionRequest`


## Правила валидации данных

1. Vigenère:
    - ключ не пустой, только английские буквы;
    - если в тексте встречается буква не из A–Z / a–z → ошибка;
    - регистр сохраняется (буквы восстанавливаются по позициям).
2. Caesar:
    - ключ — строка из цифр (целое число сдвига);
    - поддерживаются английские и русские буквы (нижний/верхний регистр сохраняется);
    - все прочие символы (пробелы, пунктуация, цифры и т.п.) не меняются.


# Как запустить локально

1. Перейти в модуль `demo`.
2. Запустить: `./mvnw spring-boot:run`.
3. По умолчанию сервер слушает на `http://localhost:8011`, это указано в `application.properties`.

<b>Примечание.</b> В `static/scriptsForIndex.js` и `static/scriptsForCaesarCipher.js` зашит адрес `https://derevyanko.braverto.com/...`.
Если вы хотите, чтобы локальные страницы отправлялись в локальный бэкенд, поменяйте fetch(...) на один из вариантов ниже.

- <b>Вариант 1. </b> `fetch('/api/processCaesarCipherEncryptionRequest', { ... })`
- <b>Вариант 2. </b> `fetch('http://localhost:8011/api/processCaesarCipherEncryptionRequest', { ... })`

После запуска бэкенда Spring Boot сам отдаёт статические файлы из resources/static. Поэтому для открытия страниц можно перейти по следующим адресам:
 - Vigenère: http://localhost:8011/index.html
 - Caesar: http://localhost:8011/CaesarCipher.html