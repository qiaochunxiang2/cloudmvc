package com.sk.cloudmvc.service;

import com.sk.cloudmvc.dao.DepartmentMapper;
import com.sk.cloudmvc.dao.UserInformationMapper;
import com.sk.cloudmvc.model.Department;
import com.sk.cloudmvc.model.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author qiaochunxiang
 * @date 2020/4/23 17:26
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UserInformationMapper userInformationMapper;

    /**
     * 查询所有部门
     *
     * @return java.util.List<com.sk.cloudmvc.model.Department>
     * @author qiaochunxiang
     * @date 16:59 2020/4/23
     **/
    public List<Department> allDepartments(String id) {
        return departmentMapper.allDepartment(id);
    }

    /**
     * 添加部门
     *
     * @param department 部门信息
     * @return boolean
     * @author qiaochunxiang
     * @date 17:03 2020/4/23
     **/
    public boolean addDepartment(Map<String, String> department) {
        return departmentMapper.addDepartment(department);
    }


    /**
     * 删除部门
     *
     * @return boolean
     * @author qiaochunxiang
     * @date 17:00 2020/4/23
     **/
    public boolean deleteDepartment(String id) {
        List<UserInformation> users = userInformationMapper.findByDid(id);
        if (users != null && users.size() > 1) {
            return false;
        }
        return departmentMapper.deleteDepartment(id);
    }
}
