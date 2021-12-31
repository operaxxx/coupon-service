package com.operaxxx.coupon.template.converter;

import com.operaxxx.coupon.template.api.beans.CouponTemplateInfo;

/**
 * @author hu kun
 * @date 2021/12/30
 */

public class CouponTemplateConverter {
    public static CouponTemplateInfo convertToTemplateInfo(com.operaxxx.coupon.template.dao.entity.CouponTemplate template) {

        return CouponTemplateInfo.builder()
                .id(template.getId())
                .name(template.getName())
                .desc(template.getDescription())
                .type(template.getCategory().getCode())
                .shopId(template.getShopId())
                .available(template.getAvailable())
                .rule(template.getRule())
                .build();
    }
}
