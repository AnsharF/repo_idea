package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

public interface PromotionAdService {

    /*
        分页查询广告信息
     */
    public PageInfo<PromotionAd> findAllPromotionByPage(PromotionAdVO promotionAdVO);

    /*
        修改广告状态
     */
    public void updatePromotionAdStatus(Integer id, Integer status);
}
