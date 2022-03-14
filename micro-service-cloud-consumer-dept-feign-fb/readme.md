openFeign Hystrix 解耦降级逻辑

注：在使用openFeign解耦降级逻辑，回退方法fallback时
#####1、将微服务的路径全路径写入方法@RequestMapping
#####2、将微服务的路径前缀写入OpenFeign注解的path中
#####使用@RequestMapping截断微服务路径前缀会导致注入重复，报错