package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

public interface ResourceService {

    /*
        资源分页&多条件查询
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO);
}
