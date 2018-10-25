package com.jpa.simple.service.impl;

import com.jpa.simple.dao.DepartmentRepository;
import com.jpa.simple.entity.SenyiDeptmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * 对实体的增删改查操作
     * @param senyiDeptmentEntity
     * @return
     */
    @Transactional
    public SenyiDeptmentEntity saveEntity(SenyiDeptmentEntity senyiDeptmentEntity){
        //数据库没有数据则认为是新增操作
        return departmentRepository.save(senyiDeptmentEntity);
        /*return departmentRepository.saveALL(List);
        这个方法会立刻把实体刷新进入缓存
        return departmentRepository.saveAndFlush(senyiDeptmentEntity);*/
    }
    @Transactional
    public void deleteEntity(int depid){
        departmentRepository.deleteById(depid);
       /*同理也有deleteAll和deleteById和delete*/
    }
    @Transactional
    public SenyiDeptmentEntity updateDeptment(SenyiDeptmentEntity senyiDeptmentEntity){
        //数据库有数据，则认为是更新的操作
      return departmentRepository.save(senyiDeptmentEntity);
    }
    @Transactional(readOnly = true)
    public SenyiDeptmentEntity findDeptmentById(int id){
       // return departmentRepository.getOne(id);
        return departmentRepository.findById(id).orElse(null);
    }

    /**
     * 查询列表的操作
     */
    @Transactional(readOnly = true)
    public List<SenyiDeptmentEntity> findList(){
        //返回所有数据
        //return departmentRepository.findAll();
        //返回一个主键集合的list
       /* List<Integer> idList = new ArrayList<Integer>();
        idList.add(1);
        idList.add(2);
        return departmentRepository.findAllById(idList);*/
        //查询按照某个字段排序
        /*Sort sort = new Sort(Sort.Direction.ASC,"dep_id");
        return departmentRepository.findAll(sort);*/
        //多个字段排序
        /*Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "dep_id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "dep_id");
        List<Sort.Order> orderList = new ArrayList<Sort.Order>();
        orderList.add(order1);
        orderList.add(order2);
        Sort sort = new Sort(orderList);
        return departmentRepository.findAll(sort);*/
        //example精准查询(即满足这个example条件的所有数据)
       /* SenyiDeptmentEntity sde = new SenyiDeptmentEntity();
        sde.setDepName("zkj");
        sde.setDeptCode("111");
        Example<SenyiDeptmentEntity> example = Example.of(sde);
        return departmentRepository.findAll(example);*/
       //example和sort配合查询
        Sort sort = new Sort(Sort.Direction.ASC,"depId");
        SenyiDeptmentEntity sde = new SenyiDeptmentEntity();
        sde.setDepId(15);
        sde.setDepName("部门15");
        sde.setDeptCode("015");
        sde.setNote("note15");
        Example<SenyiDeptmentEntity> example = Example.of(sde);
        return departmentRepository.findAll(example,sort);
       //ExampleMatcher（匹配查询）
        /*SenyiDeptmentEntity sde = new SenyiDeptmentEntity();
        sde.setDepName("部门");
        sde.setDeptCode("00");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("depName",ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())//以此开头并且忽略大小写
                .withMatcher("deptCode",ExampleMatcher.GenericPropertyMatchers.contains())//包含
                //.withMatcher("note",ExampleMatcher.GenericPropertyMatchers.endsWith())//以此结尾
                .withIgnorePaths("depId")//忽略属性不管dep_id是什么值都不加入查询条件
                .withIgnorePaths("note");
        //1.8JDK可以使用lamda表达式
        Example example = Example.of(sde,matcher);
        return departmentRepository.findAll(example);*/
       //计算总数
        //long num = departmentRepository.count();
        //计算有条件的总数
        /*SenyiDeptmentEntity sde = new SenyiDeptmentEntity();
        sde.setDepName("zkj");
        sde.setDeptCode("111");
        Example<SenyiDeptmentEntity> example = Example.of(sde);
        long num1 = departmentRepository.count(example);*/
    }

    /**
     * 分页查询列表的操作
     */

    @Transactional(readOnly = true)
    public List<SenyiDeptmentEntity> findListBypage(int page, int size){
        //返回所有数据的分页
       /* PageRequest pageRequest =  PageRequest.of(page,size);//没有加上排序字段
        Page<SenyiDeptmentEntity> pageObject = departmentRepository.findAll(pageRequest);
        int totalPage = pageObject.getTotalPages();//总的页数
        long count = pageObject.getTotalElements();//返回总数
        List<SenyiDeptmentEntity> list = pageObject.getContent();//返回此次查询的分页结果集
        return list;*/

        //加上查询条件以及排序的分页
        Sort sort = new Sort(Sort.Direction.ASC,"depId");
        SenyiDeptmentEntity sde = new SenyiDeptmentEntity();
        sde.setDepId(15);
        sde.setDepName("部门15");
        sde.setDeptCode("015");
        sde.setNote("note15");
        Example<SenyiDeptmentEntity> example = Example.of(sde);
        PageRequest pageRequest =  PageRequest.of(page,size,sort);//加上排序字段
        Page<SenyiDeptmentEntity> pageObject = departmentRepository.findAll(example,pageRequest);
        int totalPage = pageObject.getTotalPages();//总的页数
        long count = pageObject.getTotalElements();//返回总数
        List<SenyiDeptmentEntity> list = pageObject.getContent();//返回此次查询的分页结果集
        return list;
    }
}
