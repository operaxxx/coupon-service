package com.operaxxx.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠券信息
 *
 * @author hu kun
 * @date 2021/12/30
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponInfo {
    private Long id;
    private Long templateId;
    private Long userId;
    private Long shopId;
    private Integer status;
    private CouponTemplateInfo template;
}
