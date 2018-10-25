# JPA-samples
1.因为jpa动态查询不友好，而QueryDSl框架非常适合多表动态查询，所以引入
2.2个depedency和一个plugin（具体查看pom文件）,dao继承QuerydslPredicateExecutor
3.生成实体之后，要先maven comipier生成查询类
4.动态拼接的项目，（有spring data jpa和JPA2方式具体查看services）
