package com.jpa.entitymanage.dao;

import com.jpa.entitymanage.entity.SenyiDeptmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Dao层
 */
@Repository
public class DepartmentRepository{

    @Autowired
    private EntityManager entityManager;

    /**
     * 对实体的增删改查操作
     * @param senyiDeptmentEntity
     * @return
     */
    public void saveEntity(SenyiDeptmentEntity senyiDeptmentEntity){
        //entityManager.persist(senyiDeptmentEntity);//假如在持久化对象中出现2份相同主键的对象，则报错,因此不建议使用
        entityManager.merge(senyiDeptmentEntity);//解决上述问题,并且类似SaveOrUpdate,建议使用这个
    }
    public void deleteEntity(int depid){
        SenyiDeptmentEntity d = entityManager.find(SenyiDeptmentEntity.class,depid);
        entityManager.remove(d);
    }
    public SenyiDeptmentEntity updateDeptment(SenyiDeptmentEntity senyiDeptmentEntity){
        SenyiDeptmentEntity sde =   entityManager.merge(senyiDeptmentEntity);
        //sde.setNote("1111111111111111");
        return sde;
    }
    public SenyiDeptmentEntity findDeptmentById(int depid){
        return entityManager.find(SenyiDeptmentEntity.class,depid);
    }


    /**
     * 查询列表的操作
     */
    public List<SenyiDeptmentEntity> findList(){

        //直接拼接写法
        String jpql ="select * from senyi_deptment where dep_name ='"+"部门8"+"' and dept_code='"+"008'";
        Query query = entityManager.createNativeQuery(jpql,SenyiDeptmentEntity.class);
        return query.getResultList();
        //原生sql写法
        /*String jpql = "select * from senyi_deptment where dep_name =?1 and dept_code=?2";
        Query query = entityManager.createNativeQuery(jpql,SenyiDeptmentEntity.class);
        query.setParameter(1,"部门7");
       query.setParameter(2,"007");
       return query.getResultList();*/
        //JPQL写法-建议使用这个，便于数据库切换
        /*String jpql = "select d from SenyiDeptmentEntity d where d.depName like ?1 and d.deptCode like ?2";
       Query query = entityManager.createQuery(jpql,SenyiDeptmentEntity.class);
       query.setParameter(1,"%部门12%");
       query.setParameter(2,"%01%");
       return query.getResultList();*/
       /* String jpql = "select d from SenyiDeptmentEntity d";
        Query query = entityManager.createQuery(jpql,SenyiDeptmentEntity.class);
        return query.getResultList();*/

    }

    /**
     * 分页查询列表的操作 5  10
     */

    public List<SenyiDeptmentEntity> findListBypage(int offset, int limit){
        String jpql = "select d from SenyiDeptmentEntity d where d.depName like ?1 and d.deptCode like ?2";
        Query query = entityManager.createQuery(jpql,SenyiDeptmentEntity.class);
        query.setParameter(1,"%部门1%");
        query.setParameter(2,"%01%");
        int count = query.getResultList().size();//总数
        return query.setFirstResult(offset).setMaxResults(limit).getResultList();//分页后的内容
    }






}
