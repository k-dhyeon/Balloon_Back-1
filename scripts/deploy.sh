#!/bin/bash

REPOSITORY=/home/ubuntu/Balloon_Back
PROJECT_NAME=Balloon
REALREPOSITORY=/home/ubuntu/Real_Balloon_Back

echo "> Build 파일 복사"

mkdir -p $REALREPOSITORY

sudo cp $REPOSITORY/zip/*.jar $REALREPOSITORY

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl jar | awk '{print $1}')

echo "현재 구동중인 어플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    sudo kill -15 $CURRENT_PID
    sleep 5
fi

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls -tr $REALREPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

sudo chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"



nohup java -jar $JAR_NAME > $REALREPOSITORY/nohup.out 2>&1 &
