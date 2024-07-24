FROM openjdk:17-jdk-alpine

WORKDIR /app

# 必須ツールをインストール
RUN apk update && apk add findutils

# GRADLE_USER_HOMEを設定
ENV GRADLE_USER_HOME=/app/.gradle

# プロジェクトファイルを全てコピー
COPY . .

# gradlewに実行権限を付与する
RUN chmod +x ./gradlew

# gradlewビルドを実行
RUN ./gradlew build

# ビルドディレクトリの内容を表示して確認する
RUN ls -la build/libs

# ビルドされたJARファイルをアプリケーションとしてコピー
COPY build/libs/*.jar app.jar

# JARファイルを実行
ENTRYPOINT ["java", "-jar", "app.jar"]
