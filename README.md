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

sudo docker run -d --name jrebel-license-server -p 9090:9090 --restart always imjcker/jrebel:latest

```

[license]: https://www.apache.org/licenses/LICENSE-2.0



# DDNS-PLUS
一个阿里云DDNS（动态域名解析）服务。

## 效果展示
邮件提示
![1](/docs/ddns-email-1.png)
![2](/docs/ddns-email-2.png)
## 技术栈
* springboot
* quartz
* 阿里云SDK
* 邮件服务
* nacos（可选）
* docker（可选）

## 使用教程
共3个版本: 
1. docker版本
2. nacos配置版本
3. 本地部署版

### docker 版

### nacos配置版

### 本地部署版

## 后期开发
支持cloudflare等其他厂商。

## 联系作者
![微信公众号](/docs/java-pro.jpg)

