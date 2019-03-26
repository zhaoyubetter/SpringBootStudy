FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=build/libs/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
# 通过 java -cp 格式启动
ENTRYPOINT ["java","-cp","app:app/lib/*","vip.sonar.springboot.study.StudyApplicationKt"]