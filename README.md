# scaffold
采用Maven组织的服务端脚手架工程
## 工程组件
* Spring4、SpringMVC和MyBatis
* 通用Mapper
* JWT认证
* 支持跨域访问
* 支持注解式权限认证
* Redis高速缓存
* RocketMQ消息队列
* Dubbo RPC通信框架
* Thrift RPC通信框架
* 基于Common Pool2封装的Thrift连接池
## common
该模块负责提供一些通用的工具类、工具包：
* 通用常量定义
* RocketMQ生产者/消费者/队列选择器实现
* Thrift连接工厂、服务
* Excel表生成插件
* Pinyin检索插件
* Token生成/解析工具类
* 文件操作类
* JedisClient ORM工具类
* MD5生成器
* Validator校验器
## mis
该模块由开发者根据自身业务需求实现，采用MVC分层模式组织，专门用来编写业务逻辑代码：
* Controller层：负责与URL进行注解映射，序列化/逆序列化参数，校验参数，调用多个Service实现业务
* Service层：负责操作多个Mapper实现业务逻辑
* Mapper层：与数据库表一一对应，负责向上层Service提供各表的增、删、改、查操作，对于复杂SQL可直接写于resources/mapping/.xml中
## rpcapi
该模块提供多种RPC服务(Dubbo/Thrift/Hession)的IDL接口定义，此模块被既包含于mis模块中作为主调使用，又被包含于rpcserver模块中作为被调服务的接口实现用。
## rpcserver
该模块是rpcapi所定义的IDL服务接口的具体实现(Java语言服务)，是一个可运行的服务性工程，入口在ServiceLaucher。
