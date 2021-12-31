package com.operaxxx.coupon.template.api.beans.rules;

/**
 * 折扣
 *
 * @author hu kun
 * @date 2021/12/30
 */

public class Discount {
    /**
     * <p>满减 - 减掉的钱数
     * <p>折扣 - 90 = 9折,  95=95折
     */
    private Long quota;

    /**
     * 最低消费
     */
    private Long threshold;
}
