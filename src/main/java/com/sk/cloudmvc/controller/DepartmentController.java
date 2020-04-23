package com.sk.cloudmvc.controller;

import com.sk.cloudmvc.model.Department;
import com.sk.cloudmvc.service.DepartmentService;
import com.sk.cloudmvc.until.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author qiaochunxiang
 * @date 2020/4/23 17:28
 */
@RestController
@Api("department")
@RequestMapping("department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/allDepartment")
    @ApiOperation(value = "查询所有部门", notes = "查询所有部门")
    public CommonResult allDepartments(@RequestParam(name = "id", required = false) String id) {
        CommonResult result = new CommonResult();
        try {
            List<Department> departments = departmentService.allDepartments(id);
            result.setData(departments);
        } catch (Exception e) {
            result.setState(500);
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @PostMapping("/addDepartment")
    @ApiOperation(value = "添加部门", notes = "添加部门")
    public CommonResult addDepartment(@RequestBody Map<String, String> department) {
        CommonResult result = new CommonResult();
        try {
            boolean addResult = departmentService.addDepartment(department);
            result.setData(addResult);
        } catch (Exception e) {
            result.setState(500);
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @DeleteMapping("/deleteDepartment")
    @ApiOperation(value = "删除部门", notes = "删除部门")
    public CommonResult deleteDepartment(String id) {
        CommonResult result = new CommonResult();
        try {
            boolean deleteResult = departmentService.deleteDepartment(id);
            result.setData(deleteResult);
        } catch (Exception e) {
            result.setState(500);
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }


}
