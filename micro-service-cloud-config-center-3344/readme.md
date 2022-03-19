分布式配置中心 - 访问git仓库文件

Config 服务端

添加消息总线（Bus）对 RabbitMQ 的支持

添加Spring Boot actuator 监控模块的依赖：
###更新springCloudConfigClient:3355客户端配置：命令行：curl -X POST "http://localhost:3344/actuator/bus-refresh/springCloudConfigClient:3355
###更新springCloudConfigClient微服务客户端配置：命令行：curl -X POST "http://localhost:3344/actuator/bus-refresh/springCloudConfigClient
###更新所有连接的客户端配置：命令行：curl -X POST "http://localhost:3344/actuator/bus-refresh/


获取git上文件信息：
http://localhost:3344/main/config-dev.properties

Spring Cloud Config 规定了一套配置文件访问规则，如下表。

#####访问规则	                                                示例
#####/{application}/{profile}[/{label}]	                /config/dev/master
#####/{application}-{profile}.{suffix}	                /config-dev.yml
#####/{label}/{application}-{profile}.{suffix}	        /master/config-dev.yml

访问规则内各参数说明如下。
#####{application}：应用名称，即配置文件的名称，例如 config-dev。
#####{profile}：环境名，一个项目通常都有开发（dev）版本、测试（test）环境版本、生产（prod）环境版本，配置文件则以 application-{profile}.yml 的形式进行区分，例如 application-dev.yml、application-test.yml、application-prod.yml 等。
#####{label}：Git 分支名，默认是 master 分支，当访问默认分支下的配置文件时，该参数可以省略，即第二种访问方式。
#####{suffix}：配置文件的后缀，例如 config-dev.yml 的后缀为 yml。

通过这套规则，我们在浏览器上就直接对配置文件进行访问。