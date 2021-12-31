package com.operaxxx.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠券计算规则
 *
 * @author hu kun
 * @date 2021/12/30
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRule {

    /**
     * 可以享受的折扣
     */
    private Discount discount;

    /**
     * w
     * 每个人最多可以领券数量
     */
    private Integer limitation;

    /**
     * 过期时间
     */
    private Long deadline;

}
