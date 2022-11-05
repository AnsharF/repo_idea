package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.dao.CourseLessonMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
     private CourseContentMapper courseContentMapper;
    @Autowired
    private CourseLessonMapper courseLessonMapper;

    /*
        根据ID查询关联的章节信息及章节信息关联的课时信息
     */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {

        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return list;
    }

    /*
        回显章节对应的课程信息(根据ID查询课程信息)
     */
    @Override
    public Course findCourseByCourseId(int courseId) {
        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    /*
        新增章节信息
     */
    @Override
    public void saveSection(CourseSection courseSection) {
        //补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        courseContentMapper.saveSection(courseSection);
    }

    /*
        修改章节信息
     */
    @Override
    public void updateSection(CourseSection courseSection) {
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSection(courseSection);
    }

    /*
        修改章节状态
     */
    @Override
    public void updateSectionStatus(Integer id, Integer status) {
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSectionStatus(courseSection);
    }

    /*
        新增课时信息
     */
    @Override
    public void saveLesson(CourseLesson courseLesson) {
        //补全信息
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);
        courseLessonMapper.saveLesson(courseLesson);

    }

    /*
        修改课时信息
     */
    @Override
    public void updateLesson(CourseLesson courseLesson) {
        courseLesson.setUpdateTime(new Date());
        courseLessonMapper.updateLesson(courseLesson);
    }

    /*
        修改课时状态
     */
    @Override
    public void updateLessonStatus(Integer id, Integer status) {
        CourseLesson courseLesson = new CourseLesson();
        courseLesson.setId(id);
        courseLesson.setStatus(status);
        courseLesson.setUpdateTime(new Date());

        courseLessonMapper.updateLessonStatus(courseLesson);


    }
}
