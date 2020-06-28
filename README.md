# Mock
# 简介
### Mock可以做什么？
* 开发过程中模拟被依赖服务
* 模拟外部系统的响应和回调
* 模拟异常响应结果，例如：难以触发的业务错误码、字符超长、请求超时等
* 微服务之间的无侵入式mock，目前只支持java
* 性能测试时，对依赖服务的mock

### 与其他开源Mock项目比，有什么特点？
* ####  配置简单、灵活 
	对http的同一个uri，可以设置多个mock结果，通过自定义匹配规则来进行区分。
	匹配规则设置合理的情况下，可同时支持不同应用场景。
	匹配规则的设置有也很简单，在管理后台页面填上“规则三要素”：变量名、比对规则、值。

* ####  使用变量
	在设置响应结果、匹配规则、异步任务时，都可以使用变量池里的变量，语法类似JSONPath。
	变量支持嵌套，即一个变量的值是另一个变量名（或者是变量名的一部分）。例如，变量池：{"shopID":"456", "shop_order_count_456":100,"shop_order_count_123":50}，要获取变量'shop_order_count_456'的值可以使用 ${shop_order_count_${shopID}}。


* #### 动态脚本
	最常用的变量池是请求参数，在复杂场景下请求参数可能满足不了需求，需要对请求参数进行处理，可以编写动态脚本生成自定义变量。可以应用的场景：对金额字段拆分、求和等，简单的对称加密实现等。
	动态脚本是在管理页面配置，随时编写随时使用，不需要重启服务。


* ####  设置回调
	“回调”在异步业务场景中应用非常多，mock一个三方系统大概率会使用到回调。可以在回调任务中进行配置，目前支持的类型：http请求、grpc请求、rabbitmq生产。


* ####  动态注入GRPC服务 
	在配置grpc服务时，可在不重启服务的情况下，下载依赖的interface，并加载注入到grpc服务中。
	扩展了proto协议，把javaPackage作为proto method的一部分，兼容了部分多项目interface定义成冲突问题，同时支持原生的proto协议


* ####  无侵入业务服务
	常规mock方案，需要业务调用方配合，修改请求到mock服务。这种方案存在一定的风险和不方便，在集成测试环境不能同时兼容mock服务和真实服务。
	无侵入方案解决了相关问题。在启动业务服务时，启动参数里通过-javaagent指定mock-agent，mock-agent会拦截所有请求，判断是否需要mock。mock规则配置合理的情况下，可应对复杂的应用场景，在集成测试环境非常有用。

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
