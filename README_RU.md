[English](README.md) I [Русский](README_RU.md)

# android-SwissTransfer

Отправляйте до 50 ГБ — бесплатно и без регистрации — Храните передачи до 30 дней.

## Содержание

- [Введение](#введение)
- [Требования](#требования)
- [Установка](#установка)
- [Использование](#использование)
- [Участие в разработке](##участие-в-разработке)
- [Лицензия](#лицензия)

## Введение

Проект `android-SwissTransfer` интегрирует
библиотеку [SwissTransfer-Multiplatform](https://github.com/Infomaniak/multiplatform-SwissTransfer), предоставляющую кроссплатформенные
возможности передачи файлов. Проект разработан для простоты использования и предоставляет разработчикам удобный способ
внедрить функции SwissTransfer в свои Android-приложения.

## Требования

Перед началом убедитесь, что у вас есть следующее:

- Вы используете компьютер под управлением Linux, macOS или Windows.
- Установлен Java Development Kit (JDK) версии 17 или выше.
- Установлена Android Studio.
- Активное интернет-соединение для загрузки зависимостей проекта.

## Установка

Для установки `android-SwissTransfer` выполните следующие шаги:

1. **Клонируйте репозиторий:**
   ```
   git clone https://github.com/Infomaniak/multiplatform-SwissTransfer.git
   cd android-SwissTransfer
   ```

2. **Откройте проект в Android Studio:**
    - Запустите Android Studio.
    - Выберите "Открыть существующий проект Android Studio".
    - Перейдите в папку с клонированным репозиторием и выберите её.

3. **Синхронизируйте проект с файлами Gradle:**
    - После открытия проекта Android Studio предложит синхронизировать проект с Gradle. Нажмите "Синхронизировать сейчас".

## Использование

Ниже приведено базовое руководство по использованию проекта `android-SwissTransfer`:

1. **Добавление библиотеки SwissTransfer-Multiplatform:**
   Убедитесь, что библиотека `SwissTransfer-Multiplatform` добавлена в зависимости файла `build.gradle`.

   ```
   dependencies {
       implementation("com.github.infomaniak.multiplatform-SwissTransfer:Core:1.0.0")
   }
   ```

2. **Инициализация библиотеки:**
   В главной активности или классе приложения инициализируйте библиотеку.

   ```
   class MainActivity: ComponentActivity() {
       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           setContentView(R.layout.activity_main)

           // Инициализация библиотеки SwissTransfer
           SwissTransferInjection()
       }
   }
   ```

## Участие в разработке

Если вы обнаружили ошибку или у вас есть предложение по улучшению, создайте issue, чтобы мы могли обсудить это. После одобрения мы или вы (в зависимости от приоритета) займемся решением и отправкой запроса на слияние. Пожалуйста, не делайте запрос на слияние без создания предварительного issue.

## Лицензия

Проект лицензирован под GPLv3. Подробнее смотрите в файле LICENSE.
