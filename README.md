# Mock
# 简介
### Mock可以做什么？
* 开发过程中模拟被依赖服务
* 模拟外部系统的响应和回调
* 模拟异常响应结果，例如：难以触发的业务错误码、字符超长、请求超时等
* 微服务之间的无侵入式mock，目前只支持java
* 性能测试时，对依赖服务的mock

### 项目子模块简介
+ #### admin
管理后台，配置mock相关规则，查看调试日志等。
+ #### mock-server-rest
http服务，接收http请求，根据mock规则，输出响应结果。通过mq添加异步任务给scheduler。

+ #### mock-server-grpc
grpc服务，接收grpc请求，根据mock规则，输出响应结果。通过mq添加异步任务给scheduler。

+ #### scheduler
接收rest和grpc服务发起的异步任务，并执行。目前支持http请求任务、grpc请求任务、rabbitmq生产任务。
+ #### plugins
扩展插件。可用于复杂加解密算法的实现等。插件执行的时机：
		 解析参数后，对请求参数处理
		 输出响应前，对响应body处理
		 异步任务执行过程中，对body的处理
+ #### mock-agent
java无侵入解决方案，目前只实现了grpc-client、httpClient。之后会支持其他的client工具，包括okhttp、RestTemplate、dubbo等。甚至可以考虑对数据库、redis等中间件的支持。
+ #### agent-protocol
mock-agent与服务端(admin)之间的业务协议。网络协议使用websocket。

# 开始使用
+ #### 编译与部署
项目使用微服务架构，需要分别部署相关服务。部署服务之前，需要先准备mysql和rabbitmq服务，代码里有提供mysql表结构。编译之前需要修改项目的配置信息，在各自项目下的yml文件中，src/main/resources/application-dev.yml。

|  服务   | 服务端口  | 占用端口 |编译命令 |启动命令 |
|  ----  | ----  | ---- | ---- | ---- |
| admin  | 8080 | 8080 | mvn clean package -DskipTests -Padmin | java -jar admin/target/mock-server-admin.jar |
| schuler  | 不需要 | 8082 | mvn clean package -DskipTests -Pscheduler | java -jar schuler/target/mock-server-schuler.jar |
| mock-server-grpc| 6565 | 6565、8083 | mvn clean package -DskipTests -Pgrpc | java -jar mock-server-grpc/target/mock-server-grpc.jar |
| mock-server-rest| 8081 | 8081 | mvn clean package -DskipTests -Prest | java -jar mock-server-grpc/target/mock-server-rest.jar |

+ #### mock配置及使用
		各项目启动完成之后，进入管理后台 http://localhost:8080 。操作手册手续会更新到管理后台界面中，不在这里介绍。
		管理后台界面比较丑，现在使用 freemarker + layui完成，以后会使用vue重构。

---
### agent 编译

mvn clean package -DskipTests -Pagent
agent编译结果目录：mock-agent/mock-agent
agent压缩包：mock-agent/agent-dist/mock-agent.zip   mock-agent/agent-dist/mock-agent.tar.gz
