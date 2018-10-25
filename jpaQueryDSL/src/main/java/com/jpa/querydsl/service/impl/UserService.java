package com.jpa.querydsl.service.impl;

import com.google.common.collect.Lists;
import com.jpa.querydsl.dao.UserRepository;
import com.jpa.querydsl.entity.QSenyiDeptmentEntity;
import com.jpa.querydsl.entity.QSenyiRoleEntity;
import com.jpa.querydsl.entity.QSenyiUserEntity;
import com.jpa.querydsl.entity.SenyiUserEntity;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring data jpa + QueryDSL 进行复杂查询
 * QueryDSL仅仅是一个通用的查询框架，专注于通过Java API构建类型安全的SQL查询。
 * Querydsl可以通过一组通用的查询API为用户构建出适合不同类型ORM框架或者是SQL的查询语句，也就是说QueryDSL是基于各种ORM框架以及SQL之上的一个通用的查询框架。
 * 借助QueryDSL可以在任何支持的ORM框架或者SQL平台上以一种通用的API方式来构建查询。目前QueryDSL支持的平台包括JPA,JDO,SQL,Java Collections,RDF,Lucene,Hibernate Search。
 * P.s.配置可以根据官网介绍来配置
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;
    //查询工厂实体
    private JPAQueryFactory queryFactory;
    //实例化控制器完成后执行该方法实例化JPAQueryFactory
    @PostConstruct
    public void initFactory()
    {
        System.out.println("开始实例化JPAQueryFactory");
        queryFactory = new JPAQueryFactory(entityManager);
    }

    //---------------------------------以下3个方法使用spring data jpa方式------------------------------------------------------------------

    /**
     * 对单表的动态查询
     * <p>
     * 查询name=?和address like
     */

    @Transactional(readOnly = true)
    public List<SenyiUserEntity> findListBySpringData(SenyiUserEntity senyiUserEntity) {
        //单表简单条件拼接where name =?1 and address like ?2-0----动态拼接方式
       /* QSenyiUserEntity qSenyiUserEntity = QSenyiUserEntity.senyiUserEntity;
        Predicate predicate = null;
        if(senyiUserEntity.getName()!=null&&!senyiUserEntity.getName().equals("")){
            predicate =qSenyiUserEntity.name.eq(senyiUserEntity.getName());//.and(qSenyiUserEntity.address.like(senyiUserEntity.getAddress()));

        }
        if(senyiUserEntity.getAddress()!=null){
            predicate = ((BooleanExpression) predicate).and(qSenyiUserEntity.address.like("%"+senyiUserEntity.getAddress()+"%"));
        }
        Iterable<SenyiUserEntity> senyiUserIterables =  userRepository.findAll(predicate);
        return Lists.newArrayList(senyiUserIterables);*/
        //单表复杂条件拼接where (name =?1 and address like ?2) or name =?3
        QSenyiUserEntity qSenyiUserEntity = QSenyiUserEntity.senyiUserEntity;
        Predicate predicate1 = qSenyiUserEntity.name.eq(senyiUserEntity.getName()).and(qSenyiUserEntity.address.like("%" + senyiUserEntity.getAddress() + "%"));
        Predicate predicate2 = qSenyiUserEntity.name.eq("2");
        Iterable<SenyiUserEntity> senyiUserIterables = userRepository.findAll(((BooleanExpression) predicate1).or(predicate2));
        return Lists.newArrayList(senyiUserIterables);

    }

    /**
     * 对单表的分页动态查询
     * where (name =?1 and address like ?2) or name =?3
     */
    @Transactional(readOnly = true)
    public Page<SenyiUserEntity> findListBypageBySpringData(int page, int size, SenyiUserEntity senyiUserEntity) {
        QSenyiUserEntity qSenyiUserEntity = QSenyiUserEntity.senyiUserEntity;
        Predicate predicate1 = qSenyiUserEntity.name.eq(senyiUserEntity.getName()).and(qSenyiUserEntity.address.like("%" + senyiUserEntity.getAddress() + "%"));
        Predicate predicate2 = qSenyiUserEntity.name.eq("2");
        //构建分页
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAll(((BooleanExpression) predicate1).or(predicate2), pageRequest);
    }

    /**
     * 子查询操作
     * select * from senyi_user
     * where dep_id in(select dep_id from senyi_department where dep_name like '%1%')
     * and role_id in(select id from senyi_role where role_name like '%1%')
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<SenyiUserEntity> findUserByZichaxunBySpringData() {
        QSenyiUserEntity qSenyiUserEntity = QSenyiUserEntity.senyiUserEntity;
        QSenyiDeptmentEntity qSenyiDeptmentEntity = QSenyiDeptmentEntity.senyiDeptmentEntity;
        QSenyiRoleEntity qSenyiRoleEntity = QSenyiRoleEntity.senyiRoleEntity;
        Predicate predicateRole = qSenyiRoleEntity.roleName.like("%1%");
        Predicate predicateDept = qSenyiDeptmentEntity.depName.like("%1%");
        Predicate predicateUser1 = qSenyiUserEntity.depId.in(JPAExpressions.select(qSenyiDeptmentEntity.depId).from(qSenyiDeptmentEntity).where(predicateDept));
        Predicate predicateUser2 = qSenyiUserEntity.roleId.in(JPAExpressions.select(qSenyiRoleEntity.id).from(qSenyiRoleEntity).where(predicateRole));
        Iterable<SenyiUserEntity> senyiUserIterables = userRepository.findAll(((BooleanExpression) predicateUser1).and(predicateUser2));
        return Lists.newArrayList(senyiUserIterables);
    }

    //---------------------------------以下方法全部使用JPA查询方式--------------------------------------------------------------------------------

    /**
     * 对单表的动态查询
     */
    @Transactional(readOnly = true)
    public List<SenyiUserEntity> findList(SenyiUserEntity senyiUserEntity) {
        QSenyiUserEntity qSenyiUserEntity = QSenyiUserEntity.senyiUserEntity;
        Predicate predicate = qSenyiUserEntity.name.like("%"+senyiUserEntity.getName()+"%");
        return this.findSenyiUserEntityByPredicate(predicate);
    }

    /**
     * 对单表的动态分页查询
     *
     */
    @Transactional(readOnly = true)
    public List<SenyiUserEntity> findListBypage(int offset, int limit, SenyiUserEntity senyiUserEntity) {
        QSenyiUserEntity qSenyiUserEntity = QSenyiUserEntity.senyiUserEntity;
        Predicate predicate = qSenyiUserEntity.name.like("%"+senyiUserEntity.getName()+"%");
        return this.findSenyiUserEntityByPredicateByPage(predicate, offset, limit);
    }

    /**
     * 多表的动态查询
     */
    @Transactional(readOnly = true)
    public List<SenyiUserEntity> findListManyTables(SenyiUserEntity senyiUserEntity) {
        QSenyiUserEntity qSenyiUserEntity = QSenyiUserEntity.senyiUserEntity;
        QSenyiDeptmentEntity qSenyiDeptmentEntity = QSenyiDeptmentEntity.senyiDeptmentEntity;
        QSenyiRoleEntity qSenyiRoleEntity = QSenyiRoleEntity.senyiRoleEntity;
        //定义动态查询条件
        Predicate predicate = qSenyiUserEntity.name.eq(senyiUserEntity.getName()).and(qSenyiRoleEntity.id.eq(9));
        //查询相关list
        List<Tuple> list = this.findUserAndDeptment(predicate);
        List<SenyiUserEntity> senyiUserEntityList = new ArrayList<>();
        for (Tuple row : list) {
            /*System.out.println("qSenyiUserEntity"+row.get(qSenyiUserEntity));
            System.out.println("qSenyiDeptmentEntity"+row.get(qSenyiDeptmentEntity));
            System.out.println("qSenyiRoleEntity"+row.get(qSenyiRoleEntity));*/
            //暂时只获取qSenyiUserEntity
            senyiUserEntityList.add(row.get(qSenyiUserEntity));
        }
        return senyiUserEntityList;
    }
    /**
     * 多表的分页动态查询
     */
    @Transactional(readOnly = true)
    public List<SenyiUserEntity> findListBypageManyTables(int offset, int limit, SenyiUserEntity senyiUserEntity) {
        QSenyiUserEntity qSenyiUserEntity = QSenyiUserEntity.senyiUserEntity;
                QSenyiDeptmentEntity qSenyiDeptmentEntity = QSenyiDeptmentEntity.senyiDeptmentEntity;
                QSenyiRoleEntity qSenyiRoleEntity = QSenyiRoleEntity.senyiRoleEntity;
                //定义动态查询条件
                //Predicate predicate = qSenyiUserEntity.name.eq(senyiUserEntity.getName()).and(qSenyiRoleEntity.id.eq(9));
                Predicate predicate = qSenyiUserEntity.name.eq(senyiUserEntity.getName());
                //查询相关list
        List<Tuple> list = this.findUserAndDeptmentBypage(predicate, offset, limit);
        List<SenyiUserEntity> senyiUserEntityList = new ArrayList<>();
        for (Tuple row : list) {
            /*System.out.println("qSenyiUserEntity"+row.get(qSenyiUserEntity));
            System.out.println("qSenyiDeptmentEntity"+row.get(qSenyiDeptmentEntity));
            System.out.println("qSenyiRoleEntity"+row.get(qSenyiRoleEntity));*/
            //暂时只获取qSenyiUserEntity
            senyiUserEntityList.add(row.get(qSenyiUserEntity));
        }
        return senyiUserEntityList;
    }

    /**
     * 子查询操作
     * select * from senyi_user
     * where dep_id in(select dep_id from senyi_department where dep_name like '%1%')
     * and role_id in(select id from senyi_role where role_name like '%1%')
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<SenyiUserEntity> findUserByZichaxun() {
        QSenyiUserEntity qSenyiUserEntity = QSenyiUserEntity.senyiUserEntity;
        QSenyiDeptmentEntity qSenyiDeptmentEntity = QSenyiDeptmentEntity.senyiDeptmentEntity;
        QSenyiRoleEntity qSenyiRoleEntity = QSenyiRoleEntity.senyiRoleEntity;
        Predicate predicateRole = qSenyiRoleEntity.roleName.like("%1%");
        Predicate predicateDept = qSenyiDeptmentEntity.depName.like("%1%");
        Predicate predicateUser1 = qSenyiUserEntity.depId.in(JPAExpressions.select(qSenyiDeptmentEntity.depId).from(qSenyiDeptmentEntity).where(predicateDept));
        Predicate predicateUser2 = qSenyiUserEntity.roleId.in(JPAExpressions.select(qSenyiRoleEntity.id).from(qSenyiRoleEntity).where(predicateRole));
        return this.findSenyiUserEntityByPredicate(((BooleanExpression) predicateUser1).and(predicateUser2));
    }

    private List<SenyiUserEntity> findSenyiUserEntityByPredicate(Predicate predicate) {
        JPAQuery<SenyiUserEntity> jpaQuery = queryFactory.selectFrom(QSenyiUserEntity.senyiUserEntity)
                .where(predicate)
                .orderBy(QSenyiUserEntity.senyiUserEntity.name.asc())
                .orderBy(QSenyiUserEntity.senyiUserEntity.depId.desc());
        //JPAQuery<SenyiUserEntity> jpaQuery = queryFactory.select(QSenyiUserEntity.senyiUserEntity).from(QSenyiUserEntity.senyiUserEntity).where(predicate);
        return jpaQuery.fetch();
    }

    private List<SenyiUserEntity> findSenyiUserEntityByPredicateByPage(Predicate predicate, int offset, int limit) {
        JPAQuery<SenyiUserEntity> jpaQuery = queryFactory.selectFrom(QSenyiUserEntity.senyiUserEntity)
                .where(predicate)
                .orderBy(QSenyiUserEntity.senyiUserEntity.id.desc())
                .offset(offset)
                .limit(limit);
        return jpaQuery.fetch();
    }

    private List<Tuple> findUserAndDeptment(Predicate predicate) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(QSenyiUserEntity.senyiUserEntity, QSenyiDeptmentEntity.senyiDeptmentEntity, QSenyiRoleEntity.senyiRoleEntity)
                .from(QSenyiUserEntity.senyiUserEntity)
                //.leftJoin(QSenyiDeptmentEntity.senyiDeptmentEntity)//也可以leftJoin或者rightJoin等外连接查询
                .join(QSenyiDeptmentEntity.senyiDeptmentEntity)//内连接查询，也可以写innerJoin
                .on(QSenyiUserEntity.senyiUserEntity.depId.eq(QSenyiDeptmentEntity.senyiDeptmentEntity.depId))
                .join(QSenyiRoleEntity.senyiRoleEntity)
                .on(QSenyiUserEntity.senyiUserEntity.roleId.eq(QSenyiRoleEntity.senyiRoleEntity.id))
                .where(predicate)
                .orderBy(QSenyiUserEntity.senyiUserEntity.id.desc());
        //上边有个注意点，String和Integer的数据类型不能比较，即使把类型转换到一致也依旧无法查询（只是暂时，个人觉得能找到办法是不同数据类型的值能比较）
        //拿到结果
        return jpaQuery.fetch();
    }

    private List<Tuple> findUserAndDeptmentBypage(Predicate predicate, int offset, int limit) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(QSenyiUserEntity.senyiUserEntity, QSenyiDeptmentEntity.senyiDeptmentEntity, QSenyiRoleEntity.senyiRoleEntity)
                .from(QSenyiUserEntity.senyiUserEntity)
                //.leftJoin(QSenyiDeptmentEntity.senyiDeptmentEntity)//也可以leftJoin或者rightJoin等外连接查询
                .join(QSenyiDeptmentEntity.senyiDeptmentEntity)//内连接查询，也可以写innerJoin
                .on(QSenyiUserEntity.senyiUserEntity.depId.eq(QSenyiDeptmentEntity.senyiDeptmentEntity.depId))
                .join(QSenyiRoleEntity.senyiRoleEntity)
                .on(QSenyiUserEntity.senyiUserEntity.roleId.eq(QSenyiRoleEntity.senyiRoleEntity.id))
                .where(predicate)
                .orderBy(QSenyiUserEntity.senyiUserEntity.name.asc())
                .orderBy(QSenyiUserEntity.senyiUserEntity.depId.desc())
                .offset(offset)
                .limit(limit);
        return jpaQuery.fetch();
    }


    /**
     *
     *
     * 总结：这个多表内连接，外连接，子查询等都可以实现 有spring data jpa方式和jpa方式（spring data jpa对多表关联查询不能使用QueryDSL（暂时未找到方法））
     * 缺点：书写较多代码
     * 综上所述:需要自己总结选择简单的方法去操作数据库
     */

}
