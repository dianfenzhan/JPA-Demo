package com.jpa.query.service.impl;

import com.jpa.query.dao.DepartmentRepository;
import com.jpa.query.entity.SenyiDeptmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public SenyiDeptmentEntity findDeptById(int id){
        //return departmentRepository.findDeptByIdJPQL(id);
        //return departmentRepository.findDeptByIdNative(id);
        //return departmentRepository.findDeptByIdNameSpace(id);
        List<Object[]> oList = departmentRepository.findDeptByIdUseShuzu(id);
        SenyiDeptmentEntity d = new SenyiDeptmentEntity();
        if(oList.size()!=0){
            Object[] o = oList.get(0);
            d.setDepName(o[1].toString());
            d.setDeptCode(o[2].toString());
            d.setDepId(Integer.parseInt(o[0].toString()));
            d.setNote(o[3].toString());
        }

        return d;
    };

    @Transactional(readOnly = true)
    public List<SenyiDeptmentEntity> findByPage(String depName, Integer page, Integer size){
        PageRequest pageRequest = PageRequest.of(page,size);
        //注意框架中like关键字不要忘记增加%
        return departmentRepository.queryDepFenye("%"+depName+"%",pageRequest).getContent();

    }
    @Transactional
    public Map<String,Object> updateNum(String depname, int depId){
        Map<String,Object> map = new HashMap<String,Object>();
        int num = departmentRepository.updateDepart(depname,depId);
        map.put("修改的记录数",num);
        return map;
    }

}
