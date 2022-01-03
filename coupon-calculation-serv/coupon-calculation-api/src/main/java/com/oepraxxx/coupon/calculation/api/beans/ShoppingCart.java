package com.oepraxxx.coupon.calculation.api.beans;

import com.operaxxx.coupon.template.api.beans.CouponInfo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author hu kun
 * @date 2022/01/04
 */

public class ShoppingCart {
    @NotEmpty
    private List<Product> products;

    private Long couponId;

    private long cost;

    // 目前只支持单张优惠券
    // 但是为了以后的扩展考虑，你可以添加多个优惠券的计算逻辑
    private List<CouponInfo> couponInfos;

    @NotNull
    private Long userId;
}
