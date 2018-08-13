1. MAC下安装docker
2. docker pull zookeeper
3. 自定义docker配置文件目录，进入
4. 编辑 docker-compose.yml 文件，内容如下:
```
    version: '2'
    services:
      zoo1:
          image: zookeeper
          restart: always
          container_name: zoo1
          ports:
              - "2181:2181"
          environment:
              ZOO_MY_ID: 1
              ZOO_SERVERS: server.1=zoo1:2888:3888 server.2=zoo2:2888:3888 server.3=zoo3:2888:3888

      zoo2:
          image: zookeeper
          restart: always
          container_name: zoo2
          ports:
              - "2182:2181"
          environment:
              ZOO_MY_ID: 2
              ZOO_SERVERS: server.1=zoo1:2888:3888 server.2=zoo2:2888:3888 server.3=zoo3:2888:3888

      zoo3:
          image: zookeeper
          restart: always
          container_name: zoo3
          ports:
              - "2183:2181"
          environment:
              ZOO_MY_ID: 3
              ZOO_SERVERS: server.1=zoo1:2888:3888 server.2=zoo2:2888:3888 server.3=zoo3:2888:3888
```
5. 这个配置文件会告诉 Docker 分别运行三个 zookeeper 镜像, 并分别将本地的 2181, 2182, 2183 端口绑定到对应的容器的2181端口上.
ZOO_MY_ID 和 ZOO_SERVERS 是搭建 ZK 集群需要设置的两个环境变量, 其中 ZOO_MY_ID 表示 ZK 服务的 id, 它是1-255 之间的整数, 必须在集群中唯一. ZOO_SERVERS 是ZK 集群的主机列表.

6. 接着我们在 docker-compose.yml 当前目录下运行:<br>
  `COMPOSE_PROJECT_NAME=zk_test docker-compose up`<br>
  即可启动 ZK 集群了.执行上述命令成功后, 接着在另一个终端中运行 docker-compose ps 命令可以查看启动的 ZK 容器:
  ```
      注意, 我们在 "docker-compose up" 和 "docker-compose ps" 前都添加了 COMPOSE_PROJECT_NAME=zk_test 这个环境变量,
      这是为我们的 compose 工程起一个名字, 以免与其他的 compose 混淆
  ```

7. 可以通过 nc 命令连接到指定的 ZK 服务器, 然后发送 stat 可以查看 ZK 服务的状态, 例如:`echo stat | nc 127.0.0.1 2181`
