# jdkを環境にインストール
FROM openjdk:jdk-alpine
VOLUME /tmp
# ワーキングディレクトリの作成
RUN mkdir /app
WORKDIR /app

ENV JAR_TARGET "COVID19_Backend-1.0.0.jar"
# 起動コマンドをシェル環境で実行
ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=docker-compose ./build/libs/${JAR_TARGET}"]