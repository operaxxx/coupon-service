package com.operaxxx.coupon.calculation.impl.template;

import com.oepraxxx.coupon.calculation.api.beans.Product;
import com.oepraxxx.coupon.calculation.api.beans.ShoppingCart;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hu kun
 * @date 2022/01/04
 */

@Slf4j
public abstract class AbstractRuleTemplate implements RuleTemplate {

    @Override
    public ShoppingCart calculate(ShoppingCart order) {
        // 获取订单总价
        Long orderTotalAmount = getTotalAmount(order.getProducts());
        // 统计 shopId 价格
        Map<Long, Long> shopIdAmount = getTotalAmountGroupByShopId(order.getProducts());
        return null;
    }

    /**
     * 按照商店 id 计算总价
     */
    protected Map<Long, Long> getTotalAmountGroupByShopId(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getShopId,
                        Collectors.summingLong(p -> p.getPrice() * p.getCount())));
    }

    /**
     * 计算订单总价
     */
    protected long getTotalAmount(List<Product> products) {
        return products.stream()
                .mapToLong(product -> product.getPrice() * product.getCount())
                .sum();
    }
}
