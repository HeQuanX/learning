# 20190704
# 这是一个用于记录学习的项目
* 20190704
    * 加入springboot演示代码
    * 加入AES加密字符串演示 `AesStringDemo.java`
    * 加入RSA加密字符串演示 `RsaDemo.java`
* 20190815
    * 加入AES加密文件演示 `AesFileDemo.java`
* 20190920
    * 加入jsr303校验演示 
* 20190921
    * 加入异常类 `ApplicationException.java`
* 20191114
    * 修改日志类名
* 20191213
    * 加入Google令牌工具 `GoogleAuthenticatorSimpleUtils.java`
* 20191220
    * 加入单例模式相关测试 `cn.crabapples.pattern23.singleton.*`
* 20191224
    * 加入简单工厂模式测试 `cn.crabapples.pattern23.factory.simplefactory.*`
    * 加入工厂方法模式测试 `cn.crabapples.pattern23.factoey.factorymethod.*`
* 20191227
    * 加入抽象工厂模式测试 `cn.crabapples.pattern23.factoey.abstractFactory.*`
* 20200117
    * 加入swagger测试 `cn.crabapples.spring.common.config.Swagger2Configure.java`
* 20200127
    * 加入jpa测试crud 
* 20200129
    * 用户操作相关逻辑实现
* 20200130
    * service加入切面拦截 `cn.crabapples.spring.config.AopConfigure.java`
* 20200224
    * 前端页面加入登陆功能
* 20200224
    * 修改springBoot启动logo
    * 加入swagger注解属性字段相关注释`cn.crabapples.spring.test.form.*`
* 20200225
    * 引入vue.js前端index页面菜单使用vue生成
    * 引入nacos作为配置中心
* 20200302
    * 修复aesKey在linux下使用相同种子生成密钥不一致问题
      * Linux下默认的算法是“NativePRNG”
      * windows下默认是“SHA1PRNG”（sun提供的算法)
* 20200303
    * 修改springCache配置,缓存至redis乱码问题`cn.crabapples.spring.common.config.RedisTemplateConfigure.java`
* 20200304
    * 修改BaseController,统一异常处理返回json格式数据`cn.crabapples.spring.common.BaseController.java`
    * 前端模板加入form序列化工具`src/main/resources/static/js/common.js`
    * 前端模板加入thymeleaf公共代码`src/main/resources/templates/common.html`
    * 登陆返回数据修改为json格式`src/main/resources/templates/common.html`
    * 加密文件的方法加入日志`cn.crabapples.demo.AesFileDemo.java`
* 20200305
    * 新增分支 springBoot-jpa-security
    * 新增分支 springBoot-jpa-shiro
    * 新增分支 springBoot-jpa-noShiro
    * 新增分支 springCloud-jpa-security
* 20200306
    * 加入springShiro认证登陆
* 20200307
    * 登陆成功根据用户返回已授权菜单列表(springBoot-jpa-noShiro)
* 20200309
    * 加入shiro授权测试
    * 集成shiro授权,登陆成功后返回所有菜单，根据thymeleaf-shiro动态显示菜单(springBoot-jpa-shiro)
* 20200318
    * 临时关闭nacos配置中心