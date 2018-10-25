# JPA-samples

1.配置DataSourceConfigurer,spring.datasource.primary 是配置在application.properties中的配置参数，且是主数据源
spring.datasource.secondary同理也是第二个数据源配置的参数
2.配置PrimaryConfigurer 注意  basePackages 要配置 dao层的地址（即继承JPARepository的接口类地址）
  packages配置实体类的路径，同时加上注解@Primary，这样对主数据源数据操作的时候和之前单数据源一样
3.同上配置从数据源（不需要@Primary注解，因此用到的时候需要@Transactional(value="transactionManagerSecondary")）
4.还有一点和单数据源不同之处在于引入Entitymanager，具体如下，不能用@AutoWired和@Resource
    @PersistenceContext(unitName  = "primaryPersistenceUnit")
    private EntityManager e1;
    @PersistenceContext(unitName  = "secondaryPersistenceUnit")
    private EntityManager e2;