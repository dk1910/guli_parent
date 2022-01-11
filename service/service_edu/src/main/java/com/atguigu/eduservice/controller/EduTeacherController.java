package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-12-15
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
//    访问地址：http://localhost:8001/eduservice/edu-teacher/findAll
//    把service注入
    @Autowired
    private EduTeacherService teacherService;

//    1 查询讲师表所有数据 rest风格
    @GetMapping("/findAll")
    @ApiOperation(value = "所有讲师列表")
    public R list(){
        List<EduTeacher> list=teacherService.list(null);
        return R.ok().data("items",list);
    }
    //    访问地址：http://localhost:8001/eduservice/edu-teacher/{id}
//    2 逻辑删除讲师的方法
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除讲师")
    public R removeTeacher(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id){
        boolean flag=teacherService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //    访问地址：http://localhost:8001/eduservice/edu-teacher/pageTeacher/{current}/{limit}
    //3 分页查询讲师的方法
    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation(value = "分页查询讲师")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        //创建Page对象
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);
        //调用方法分页
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        teacherService.page(pageTeacher,null);

        long total=pageTeacher.getTotal();//总记录数
        List<EduTeacher> records=pageTeacher.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
    //    访问地址：http://localhost:8001/eduservice/edu-teacher/pageTeacher/{current}/{limit}
    //3 分页查询讲师的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody TeacherQuery teacherQuery){
        //创建page对象
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();
        //多条件查询
        //mybatisplus 动态sql
        //判断条件值是否为空，如果不为空拼接条件
        String name=teacherQuery.getName();
        Integer level=teacherQuery.getLevel();
        String begin=teacherQuery.getBegin();
        String end=teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            //构建条件
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.le("gmt_modified",end);
        }
        //调用方法实现条件查询分页
        teacherService.page(pageTeacher,wrapper);
        long total=pageTeacher.getTotal();//总记录数
        List<EduTeacher> records=pageTeacher.getRecords();//数据List集合
        return R.ok().data("total",total).data("rows",records);
    }
}

