package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /*
        多条件课程列表查询
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);


    /*
        添加课程及讲师信息
     */
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
        根据id获取课程信息(根据课程ID查询课程信息及关联的讲师信息)
     */
    public CourseVO findCourseById(Integer id);

    /*
        修改课程和讲师信息
     */
    public void updateCourseOrTeacher(CourseVO courseVO);

    /*
        修改课程状态
     */
    public void updateCourseStatus(int courseId, int status);
}
