package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-12-15
 */
@Mapper
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

    @Select("select * from edu_teacher")
    List<EduTeacher> list1();

}
