package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /*
        分页查询广告信息
     */
    public List<PromotionAd> findAllPromotionByPage();

    /*
        修改广告状态
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
