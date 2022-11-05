package com.lagou.dao;

import com.lagou.domain.CourseLesson;

public interface CourseLessonMapper {

    /*
        新建课时信息
     */
    public void saveLesson(CourseLesson courseLesson);

    /*
        修改课时信息
     */
    public void updateLesson(CourseLesson courseLesson);

    /*
        修改课时状态
     */
    public void updateLessonStatus(CourseLesson courseLesson);
}
