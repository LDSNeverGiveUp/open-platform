package com.zhj.rz.test;

import com.zhj.dao.SysRoleDAO;
import com.zhj.service.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMybatis {
    @Autowired
    SysRoleDAO roleDAO;
    @Autowired
    SysRoleService roleService;
    @Test
    public void testMybatis(){
        /*List<RoleCount> roleCounts = roleDAO.queryRoleCount();
        for (RoleCount roleCount : roleCounts) {
            System.out.println(roleCount);
        }*/
        Map<String, List<Object>> stringListMap = roleService.queryRoleCount();
        for (String s : stringListMap.keySet()) {
            System.out.println(s+":"+stringListMap.get(s));
        }
    }

}
