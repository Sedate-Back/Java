# Spring

此模块讲述关于Spring的概念、组件、注释等相关内容

## 概念

Spring的核心，就是为了降低我们开发中存在的耦合现象，通过IOC和AOP思想，实现对对象创建依赖的解耦。

- IOC：反转控制
  - Spring提供了一个容器，用来存储所有的我们所需要使用的Bean类，当然也可以手动控制不存
  - 所有的对象由IOC容器管理和创建
- Bean：实例类
  - 生命周期：在我们引用bean类执行对应的方法的时候，Bean类就会生成，在生成的时候对应的方法
    - init方法的执行：初始化方法
    - destroy方法的执行：Bean类摧毁时执行的方法
  - 作用范围：当该模块引用同一个bean的时候，是同一个对象，还是不同的对象
    - 同一个对象是单例对象 singleton（默认）
    - 不同对象是 `等会查`
- DI：依赖注入
  - 当我们多个Bean类存在某种关联或依赖的时候，就需要使用DI进行注入
  - 有两种方式
    - 构造方法（空参构造
    - set方法
  - 自动装配：byType（根据类型） or byName（根据类名）
  - 注入的类型
    - 引用数据类型
    - 基本数据类型
    - 集合类型
    - 外部Properties
    - 第三方数据源
- Spring核心容器
  - 用来获取模块中所有的Bean及执行Bean中的方法
- **注解开发**（重要）
  - `@Component` => 在类上面使用这个注解，表示定义Bean
    - `@Component("BookDao")`
    - 衍生注解：
    - `@Controller` 表现层Bean
    - `@Service`  业务层Bean
    - `@Repository` 数据层Bean
  - `@Configuration`  和 `@ComponentScan(com.....)` => 用来代替xml注解，定义配置类
    - 第一个是定义该类为配置类，第二个为该项目的Bean从哪里搜索来
  - `@Scope("singleton")` Bean类的作用范围
  - `@PostConstruct`  初始化方法 and `@PreDestroy` 摧毁时方法
  - `@Autowired` 自动注解 and `@Qualifier("bookDao")` 自动注解的类型
  - `@Value("")` 对简单数据类型的注入
  - `@PropertySource(path)` 用来放在配置文件上，path写properties配置文件的名称，就可以在模块中使用EL表达式引用数据
  - `@Bean` 表示当前方法的返回值是一个bean对象，添加到IOC容器中
  - `@Import(path)` 可以导入其他的配置信息和类
  -  `@RunWith(class)` and `@ContextConfiguration(classes={xxxx})` 在进行Junit整合时需要用到
- AOP：面向切面编程
  - 可以理解为如果我们想把所有的方法输出前或后或之间等等加上某一个特定的执行语句，这时候我们要用到这个编程思想
  - 在执行切入点对于的方法的时候，用的是**代理的思想**，而不是真的在运行Bean的方法
  - 注解
    - `@Aspect`  设置当前类为切面类
    - `@Pointcut("execution (xxxxxx)")` 设置切入点，xxxx表示对应的Bean类的方法
    - `@Before("pt()")`  前置通知
    - `@After`  后置通知
    - `@AfterReturning` 有返回值后，返回值拿到后通知，没有的话就效果跟后置通知一致
    - `@AfterThrowing` 报错的时候输出
    - `@Around` 环绕通知
    - `@EnableAspectJAutoProxy` 在配置类上输入，可以开启AOP功能
- 事务处理：将两个方法或多个方法看作一个整体，若其中一个失败了，另一个默认失败；也可以通知事务不在同一个事务组中，可以实现多种配置
  - `@Transactional`  配置当前接口方法具体有事务
  - `@EnableTransactionManagement` 在配置类上开启注解式事务驱动

## 案例

### 配置文件案例

顾名思义，配置文件配置Spring，就是通过xml文件这种方式，对Bean类进行创建和绑定，同时需要DI的就进行DI

可以引用的数据类型

- 引用数据类型（就是我们自己定义的Bean类）
- 基本数据类型（String、Integer、Long。。。）
- 第三方数据源（Mysql）



注：如果要读取外部的properties文件，就需要**开辟一个新的Xml：context空间**，用来读取这个类型的文件，进行内容的应用，同时用**EL表达式**就可以引用文件中的数据



由于配置文件的方式过于入门，这里不多介绍，入门案例可以看模块`Test-1-IOCAndDI`



### 注解配置模式

