package com.sk.cloudmvc.dao;

import com.sk.cloudmvc.model.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qiaochunxiang
 * @date 2020/4/23 16:58
 */
@Repository
public interface CompanyMapper {

    /**
     * 查询所有公司
     *
     * @return java.util.List<com.sk.cloudmvc.model.Company>
     * @author qiaochunxiang
     * @date 16:59 2020/4/23
     **/
    List<Company> allCompany();

    /**
     * 添加公司
     *
     * @param company 公司信息
     * @return boolean
     * @author qiaochunxiang
     * @date 17:03 2020/4/23
     **/
    boolean addCompany(Company company);


    /**
     * 删除公司
     *
     * @param id 公司id
     * @return boolean
     * @author qiaochunxiang
     * @date 17:00 2020/4/23
     **/
    boolean deleteCompany(String id);

    /**
     * 修改公司信息
     *
     * @param company 公司信息
     * @return boolean
     * @author qiaochunxiang
     * @date 21:03 2020/4/23
     **/
    boolean updateCompany(Company company);
}
