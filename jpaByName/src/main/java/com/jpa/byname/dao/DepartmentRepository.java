package com.jpa.byname.dao;

import com.jpa.byname.entity.SenyiDeptmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**常用关键字
 * And,Or,Betwween,lessThan,GraterThan,GreaterThanEqual,After,Before,
 * IsNull,NotNull,OrderBy,Not,in,NotIn,True,false,IgnoreCase,Like,NotLike
 */
public interface DepartmentRepository extends JpaRepository<SenyiDeptmentEntity,Integer> {
    public List<SenyiDeptmentEntity> findByDepName(String depName);

    public List<SenyiDeptmentEntity> findByDepNameAndDeptCode(String depName, String deptCode);

    public List<SenyiDeptmentEntity> findByDepNameOrDeptCode(String depName, String deptCode);

    public List<SenyiDeptmentEntity> findByDepNameNotInAndDeptCodeInAndNoteInOrderByDepNameDepNameDesc(List<String> depnamelist,List<String> depcodelist,List<String> notelist);

    public List<SenyiDeptmentEntity> findFirst10ByDepName(String depName);

    //public List<SenyiDeptmentEntity> findByDepNameAndDeptCode(String name,String depcode);

    /**
     * 优点:使用名字极大地简化开发
     * 缺点：1.方法名字过于复杂，一旦名字写错很难找到相应的bug，因此这种情况不符合标准化开发
     *       2.只能查询，删除，无法新增和修改--个人简介，不一定正确
     * 总结 有利有弊，谨慎使用
     *
     */

}
