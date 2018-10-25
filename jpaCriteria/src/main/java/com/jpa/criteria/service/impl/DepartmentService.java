package com.jpa.criteria.service.impl;

import com.jpa.criteria.dao.DepartmentRepository;
import com.jpa.criteria.entity.SenyiDeptmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EntityManager entityManager;

    /**
     * 对单表的动态查询
     *
     * @param senyiDeptmentEntity
     * @return
     */
    @Transactional(readOnly = true)
    public List<SenyiDeptmentEntity> findList(SenyiDeptmentEntity senyiDeptmentEntity) {
        List<SenyiDeptmentEntity> sdeList = null;
        Specification<SenyiDeptmentEntity> specification = new Specification<SenyiDeptmentEntity>() {
            @Override
            public Predicate toPredicate(Root<SenyiDeptmentEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                 /*  List<Predicate> predicates = new ArrayList<Predicate>();
                   predicates.add(criteriaBuilder.like(root.get("depName"),"%"+senyiDeptmentEntity.getDepName()+"%" ));//相似查询
                   predicates.add(criteriaBuilder.like(root.get("deptCode"),"%"+senyiDeptmentEntity.getDeptCode()+"%"));//大于或等于
                   predicates.add(criteriaBuilder.isNotNull(root.get("note")));//非空*/
                   //return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                   //return criteriaBuilder.and(predicates.get(0),predicates.get(1),predicates.get(2));
                  //or形式复杂条件查询（where a= 1 or (b=2 and c=3)）
                  //return criteriaBuilder.or(predicates.get(0),criteriaBuilder.and(predicates.get(1),predicates.get(2)));
                  //推荐以下方式，这样无需new多个对象
                Predicate predicate1 = criteriaBuilder.equal(root.get("depName"), "%" + senyiDeptmentEntity.getDepName() + "%");
                predicate1 = criteriaBuilder.and(criteriaBuilder.like(root.get("deptCode"), "%" + senyiDeptmentEntity.getDeptCode() + "%"), predicate1);
                predicate1 = criteriaBuilder.and(criteriaBuilder.isNotNull(root.get("note")), predicate1);
                return predicate1;
                 /*return cb.and(p1, p2);根据spring-data-jpa的源码，可以返回一个Predicate，框架内部会自动做query.where(p)的操作
                 也可以直接在这里query.where(p)，然后返回null
                推荐我这样的书写方式，即第一种*/
            }
        };
        //sdeList = this.departmentRepository.findAll(specification);
        //单个字段排序
        Sort sort = new Sort(Sort.Direction.DESC,"deptCode");
        sdeList = this.departmentRepository.findAll(specification,sort);
        return sdeList;
    }

    /**
     * 对单表的分页动态查询
     *
     * @param page
     * @param size
     * @return
     */
    @Transactional(readOnly = true)
    public Page<SenyiDeptmentEntity> findListBypage(int page, int size, SenyiDeptmentEntity senyiDeptmentEntity) {
        Specification<SenyiDeptmentEntity> specification = new Specification<SenyiDeptmentEntity>() {
            @Override
            public Predicate toPredicate(Root<SenyiDeptmentEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(criteriaBuilder.like(root.get("depName"), "%" + senyiDeptmentEntity.getDepName() + "%"));//相似查询
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("deptCode"), senyiDeptmentEntity.getDeptCode()));//大于或等于
                predicates.add(criteriaBuilder.isNotNull(root.get("note")));//非空
                //and形式
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        //PageRequest pageRequest = PageRequest.of(offset, size);
        Sort sort = new Sort(Sort.Direction.DESC,"deptCode");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        return departmentRepository.findAll(specification, pageRequest);
    }

    /**利用EntityManager
     * where (dep_Name = '部门5' and dept_code like '%005%') or(dep_Name = '部门6' and dept_code like '%006%')
     * @param offset
     * @param limit
     * @return
     */
    public List<SenyiDeptmentEntity> findListByCriteria(int offset, int limit){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SenyiDeptmentEntity> criteriaQuery = criteriaBuilder.createQuery(SenyiDeptmentEntity.class);
        Root<SenyiDeptmentEntity> senyiDeptmentEntityRoot = criteriaQuery.from(SenyiDeptmentEntity.class);
        //单个条件
        /*Predicate condition = criteriaBuilder.like(senyiDeptmentEntityRoot.get("depName"),"部门");
        criteriaQuery.where(condition);*/
        //多个条件
        Predicate condition1 = criteriaBuilder.equal(senyiDeptmentEntityRoot.get("depName"),"部门5");
        Predicate condition2 = criteriaBuilder.like(senyiDeptmentEntityRoot.get("deptCode"),"%005%");

        Predicate condition3 = criteriaBuilder.equal(senyiDeptmentEntityRoot.get("depName"),"部门6");
        Predicate condition4 = criteriaBuilder.like(senyiDeptmentEntityRoot.get("deptCode"),"%006%");
        //用criteriaBuilder操作查询条件
        criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.and(condition1,condition2),criteriaBuilder.and(condition3,condition4)));
        TypedQuery< SenyiDeptmentEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        //List< SenyiDeptmentEntity> resultList = typedQuery.getResultList();
        List< SenyiDeptmentEntity> resultList = typedQuery.setFirstResult(offset).setMaxResults(limit).getResultList();//分页
        return resultList;
    }

    /**
     *
     *
     * 总结：1.太实用啦！单表所有情况都可以用criteria查询解决，并且面向对象，安全(尤其适合动态查询)
     *       2.多表也可以实现，但是存在一个致命的缺点，
     *       多表关联需要在字段上定义@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "project_id")注解在一个属性上面，这个属性不是基本类型是另一个Entity
     *       因此这样并会导致不好维护，我们一般是使用类的基本属性作为字段
     *       3.因此这边建议使用Spring data jpa + QueryDSL 进行多表复杂动态查询
     */

}
