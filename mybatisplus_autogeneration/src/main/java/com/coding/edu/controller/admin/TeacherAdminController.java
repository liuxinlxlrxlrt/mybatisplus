package com.coding.edu.controller.admin;

import com.coding.edu.entity.Teacher;
import com.coding.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/edu/teacher")
@Api(tags = "教育中心管理")
public class TeacherAdminController {
    @Autowired
    private TeacherService teacherService;

    //REST ful API 查询全部的老师
    @GetMapping
    @ApiOperation(value = "查询老师",notes = "查询老师")
    public List<Teacher> list(){
        return teacherService.list(null);
    }

    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
        return  teacherService.removeById(id);
    }

}
