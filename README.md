# Jrebel License Server
一个基于spring boot的jrebel激活服务。  
> A spring boot based Jrebel License Server.  

docker镜像地址[点这里](https://hub.docker.com/repository/docker/imjcker/jrebel)  
> docker image available [here](https://hub.docker.com/repository/docker/imjcker/jrebel)

关注公众号【天府书虫】，发送关键字"jrebel"获取激活地址。  
![wechat](/docs/wechat.jpg)

博客小程序  
![mini-program](/docs/mini-program.jpg)

## Usage (自建教程)
![usage](/docs/usage.png)
```shell script
java -jar jrebel-license-server-1.0.0.jar 

# run daemond

nohup java -jar jrebel-license-server-1.0.0.jar >/dev/null 2>&1 &

# for docker users

sudo docker run -d --name jrebel-license-server -p 9090:9090 --restart always imjcker/jrebel-license-server:latest

```

[license]: https://www.apache.org/licenses/LICENSE-2.0


