# Jrebel License Server

A spring boot based Jrebel License Server. 

docker image available [here](http://hub.docker.com/imjcker/jrebel)

## Usage

```shell script
java -jar jrebel-license-server-1.0.0.jar 

# run daemond

nohup java -jar jrebel-license-server-1.0.0.jar >/dev/null 2>&1 &

# for docker users

sudo docker run -d --name jrebel-license-server -p 9090:9090 --restart always imjcker/jrebel:latest

```

