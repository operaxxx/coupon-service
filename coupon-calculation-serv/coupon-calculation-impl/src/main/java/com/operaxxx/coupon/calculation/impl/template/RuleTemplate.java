package com.operaxxx.coupon.calculation.impl.template;

import com.oepraxxx.coupon.calculation.api.beans.ShoppingCart;

/**
 * @author hu kun
 * @date 2022/01/04
 */
public interface RuleTemplate {

    /**
     * 计算优惠券
     */
    ShoppingCart calculate(ShoppingCart settlement);

}
