#!/usr/bin/env bash

VM_OPTS="-Xms4096m -Xmx4096 -Xss32M"
SPB_OPTS="--spring.profile.active=dev"
APP_LOCATION="/root/ddns/ddns-0.0.1.jar"
APP_NAME="ddns-0.0.1.jar"
PID_CMD="ps -ef|grep $APP_NAME | grep -v grep | awk '{print \$2}'"

start(){
  PID=$(eval $PID_CMD)
  if [[ -n $PID ]]; then
    echo "$APP_NAME is already running, PID is $PID"
  else
    nohup java "$VM_OPTS" -jar $APP_LOCATION $SPB_OPTS > /dev/null 2>&1 &
    echo "nohup java $VM_OPTS -jar $APP_LOCATION $SPB_OPTS > /dev/null 2>&1 &"
    PID=$(eval $PID_CMD)
    if [[ -n $PID ]]; then
      echo "start $APP_NAME succeed, PID is $PID"
    else
      echo "start $APP_NAME failed."
    fi
  fi
}

stop(){
  PID=$(eval $PID_CMD)
  if [[ -n $PID ]]; then
    kill -15 $PID
    sleep 3
    PID=$(eval $PID_CMD)
    if [[ -n $PID ]]; then
      echo "stop $APP_NAME failed by using kill -15 $PID, begin to kill -9 $PID"
      kill -9 $PID
      sleep 2
      echo "stop $APP_NAME succeed"
    else
      echo "stop $APP_NAME succeed"
    fi
  fi
}

restart(){
  stop
  start
}

status(){
  PID=$(eval $PID_CMD)
  if [[ -n $PID ]]; then
    echo "$APP_NAME is running, PID is $PID"
  else
    echo "$APP_NAME is not running"
  fi
}

info(){
  echo "APP_LOCATION: $APP_LOCATION"
  echo "APP_NAME: $APP_NAME"
  echo "VM_OPTS: $VM_OPTS"
  echo "SPB_OPTS: $SPB_OPTS"
}

help(){
  echo "available commands: start, stop, restart, status, info, help"
}

case $1 in
start)
  start
  ;;
stop)
  stop
  ;;
restart)
  restart
  ;;
status)
  status
  ;;
info)
  info
  ;;
help)
  help
  ;;
*)
  help
  ;;
esac
exit $?