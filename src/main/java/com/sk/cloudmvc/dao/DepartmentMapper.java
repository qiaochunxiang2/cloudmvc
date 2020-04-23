package com.sk.cloudmvc.dao;

import com.sk.cloudmvc.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author qiaochunxiang
 * @date 2020/4/23 17:13
 */
@Repository
public interface DepartmentMapper {

    /**
     * 查询所有部门
     *
     * @param cId 公司id
     * @return java.util.List<com.sk.cloudmvc.model.Department>
     * @author qiaochunxiang
     * @date 16:59 2020/4/23
     **/
    List<Department> allDepartment(String cId);

    /**
     * 添加部门
     *
     * @param department 部门信息
     * @return boolean
     * @author qiaochunxiang
     * @date 17:03 2020/4/23
     **/
    boolean addDepartment(Map<String, String> department);


    /**
     * 删除部门
     *
     * @param id 部门id
     * @return boolean
     * @author qiaochunxiang
     * @date 17:00 2020/4/23
     **/
    boolean deleteDepartment(String id);

    /**
     * 查询具体部门
     *
     * @param id 部门id
     * @return com.sk.cloudmvc.model.Department
     * @author qiaochunxiang
     * @date 22:31 2020/4/23
     **/
    Department findById(String id);
}
