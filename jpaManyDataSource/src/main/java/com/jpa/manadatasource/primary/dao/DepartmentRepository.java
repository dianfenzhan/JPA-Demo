package com.jpa.manadatasource.primary.dao;

import com.jpa.manadatasource.primary.entity.SenyiDeptmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Query查询
 */
public interface DepartmentRepository extends JpaRepository<SenyiDeptmentEntity,Integer> {

    //JPQL
    @Query("select d from SenyiDeptmentEntity d where d.depId=?1")
    public SenyiDeptmentEntity findDeptByIdJPQL(int id);

    //原生sql
    @Query(value = "select * from senyi_deptment  where dep_id=?1",nativeQuery = true)
    public SenyiDeptmentEntity findDeptByIdNative(int id);

    //支持命名参数
    @Query("select d from SenyiDeptmentEntity d where d.depId=:depId")
    public SenyiDeptmentEntity findDeptByIdNameSpace(@Param("depId") int depId);//用命名参数的时候这里必需要加上@Param注解

    //Object[]数组接收
    @Query("select d.depId,d.depName,d.deptCode,d.note from SenyiDeptmentEntity d where d.depId=?1")
    public List<Object[]> findDeptByIdUseShuzu(int id);

    //使用Pageable和Sort完成翻页或者排序
    @Query("select d from SenyiDeptmentEntity d where d.depName like ?1")
    public Page<SenyiDeptmentEntity> queryDepFenye(String depName, Pageable pageable);

    //搭配@Modifying更新或者删除数据,必须要在事务操作下面，因此service类加上@Transactional注解
    @Modifying
    @Query("update SenyiDeptmentEntity d set d.depName=?1 where d.depId=?2")
    public int updateDepart(String name, int depId);

    /**
     * 思考:对单个条件，多个条件可以完成
     * 但是针对 where name =1 or(name =2 and age = 3)这样的复杂条件查询能满足吗？
     */

}
