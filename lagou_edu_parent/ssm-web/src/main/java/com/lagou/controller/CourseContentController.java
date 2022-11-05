package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;


    /*
        根据ID查询关联的章节信息及章节信息关联的课时信息
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId) {

        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true,200,"章节及课时内容查询成功",list);
        return responseResult;

    }

    /*
        回显章节对应的课程信息(根据ID查询课程信息)
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId) {

        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200,"查询课程内容成功", course);
        return responseResult;
    }

    /*
        新增或修改章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection) {


        if (null == courseSection.getId()) {
            //新增
            courseContentService.saveSection(courseSection);

            return new ResponseResult(true,200,"新增章节成功",null);
        } else {
            //修改
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true,200,"修改章节成功",null);
        }
    }

    /*
        修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id, Integer status) {

        courseContentService.updateSectionStatus(id,status);
        Map map = new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true,200,"修改章节状态成功",map);
    }

    /*
        新增课时信息
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson) {


        //新增
        /*courseContentService.saveLesson(courseLesson);
        return new ResponseResult(true,200,"新增课时成功",null);*/
        if (null == courseLesson.getId()) {
            //新增
            courseContentService.saveLesson(courseLesson);
            return new ResponseResult(true,200,"新增课时成功",null);
        } else {
            //修改
            courseContentService.updateLesson(courseLesson);
            return new ResponseResult(true,200,"修改课时成功",null);
        }
    }

    /*
        修改课时状态
     */
    @RequestMapping("/updateLessonStatus")
    public ResponseResult updateLessonStatus(Integer id, Integer status) {
        courseContentService.updateLessonStatus(id, status);
        Map map = new HashMap();
        map.put("status",status);
        return new ResponseResult(true,200,"修改课时状态成功",map);
    }
}
