package com.sk.cloudmvc.controller;

import com.sk.cloudmvc.model.Company;
import com.sk.cloudmvc.service.CompanyService;
import com.sk.cloudmvc.until.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qiaochunxiang
 * @date 2020/4/23 17:07
 */
@RestController
@Api("company")
@RequestMapping("company")
public class CompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;


    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有公司", notes = "查询所有公司")
    public CommonResult findAll() {
        CommonResult result = new CommonResult();
        try {
            List<Company> companies = companyService.allCompany();
            result.setData(companies);
        } catch (Exception e) {
            result.setState(500);
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @PostMapping("/addCompany")
    @ApiOperation(value = "添加公司", notes = "添加公司")
    public CommonResult addCompany(@RequestBody Company company) {
        CommonResult result = new CommonResult();
        try {
            boolean addResult = companyService.addCompany(company);
            result.setData(addResult);
        } catch (Exception e) {
            result.setState(500);
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @DeleteMapping("/deleteCompany")
    @ApiOperation(value = "删除公司", notes = "删除公司")
    public CommonResult deleteCompany(String id) {
        CommonResult result = new CommonResult();
        try {
            boolean deleteResult = companyService.deleteCompany(id);
            result.setData(deleteResult);
        } catch (Exception e) {
            result.setState(500);
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @PostMapping("/updateCompany")
    @ApiOperation(value = "修改公司信息", notes = "修改公司信息")
    public CommonResult updateCompany(@RequestBody Company company) {
        CommonResult result = new CommonResult();
        try {
            boolean updateResult = companyService.updateCompany(company);
            result.setData(updateResult);
        } catch (Exception e) {
            result.setState(500);
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }
}
