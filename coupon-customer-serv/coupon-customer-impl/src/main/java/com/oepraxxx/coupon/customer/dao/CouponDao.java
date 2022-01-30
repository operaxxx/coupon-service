package com.oepraxxx.coupon.customer.dao;

/**
 * @author hu kun
 * @date 2022/01/30
 */
public interface CouponDao {

  long countByUserIdAndTemplateId(Long userId, Long templateId);

}
