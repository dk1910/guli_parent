package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-12-15
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
//    访问地址：http://localhost:8001/eduservice/edu-teacher/findAll
//    把service注入
    @Autowired
    private EduTeacherService teacherService;

//    1 查询讲师表所有数据 rest风格
    @GetMapping("/findAll")
    public List<EduTeacher> list1(){
        List<EduTeacher> list=teacherService.list1();
        return list;
    }

}

