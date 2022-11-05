package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /*
        根据ID查询关联的章节信息及章节信息关联的课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
        回显章节对应的课程信息(根据ID查询课程信息)
     */
    public Course findCourseByCourseId(int courseId);

    /*
        新增章节信息
     */
    public void saveSection(CourseSection courseSection);

    /*
        修改章节信息
     */
    public void updateSection(CourseSection courseSection);

    /*
        修改章节状态
     */
    public void updateSectionStatus(Integer id, Integer status);

    /*
        新增课时信息
     */
    public void saveLesson(CourseLesson courseLesson);

    /*
        新增课时信息
     */
    public void updateLesson(CourseLesson courseLesson);

    /*
        修改课时状态
     */
    public void updateLessonStatus(Integer id, Integer status);
}
