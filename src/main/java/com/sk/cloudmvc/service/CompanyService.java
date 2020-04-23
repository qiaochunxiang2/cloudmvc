package com.sk.cloudmvc.service;

import com.sk.cloudmvc.dao.CompanyMapper;
import com.sk.cloudmvc.model.Company;
import com.sk.cloudmvc.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author qiaochunxiang
 * @date 2020/4/23 17:02
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;


    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询所有公司
     *
     * @return java.util.List<com.sk.cloudmvc.model.Company>
     * @author qiaochunxiang
     * @date 16:59 2020/4/23
     **/
    public List<Company> allCompany() {
        return companyMapper.allCompany();
    }

    /**
     * 添加公司
     *
     * @param company 公司信息
     * @return boolean
     * @author qiaochunxiang
     * @date 17:03 2020/4/23
     **/
    public boolean addCompany(Company company) {
        String id = UUID.randomUUID().toString().replace("-", "");
        company.setId(id);
        return companyMapper.addCompany(company);
    }

    /**
     * 删除公司
     *
     * @return boolean
     * @author qiaochunxiang
     * @date 17:00 2020/4/23
     **/
    public boolean deleteCompany(String id) {
        List<Department> departments = departmentService.allDepartments(id);
        if (departments != null && departments.size() > 0) {
            return false;
        }
        return companyMapper.deleteCompany(id);
    }

    /**
     * 修改公司信息
     *
     * @param company 公司信息
     * @return boolean
     * @author qiaochunxiang
     * @date 21:05 2020/4/23
     **/
    public boolean updateCompany(Company company) {
        return companyMapper.updateCompany(company);
    }
}
