FROM maven:3.9.4-eclipse-temurin-21

# Устанавливаем внутри контейнера Chrome + вспомогательные пакеты
RUN apt-get update && \
    apt-get install -y wget gnupg2 unzip && \
    wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Устанавливаем Allure CLI
ARG ALLURE_VERSION=2.32.0
RUN wget -q -O /tmp/allure.zip \
    "https://github.com/allure-framework/allure2/releases/download/${ALLURE_VERSION}/allure-${ALLURE_VERSION}.zip" && \
    unzip /tmp/allure.zip -d /opt/allure && \
    rm /tmp/allure.zip && \
    ln -s /opt/allure/allure-${ALLURE_VERSION}/bin/allure /usr/bin/allure

WORKDIR /app
COPY . .

# Скачиваем зависимости (опционально)
RUN mvn dependency:resolve

# Генерация Allure-отчёта сразу после тестов
CMD ["/bin/sh", "-c", "mvn clean test -Dmaven.test.failure.ignore=true && allure generate allure-results --clean -o allure-report"]