package com.oepraxxx.coupon.customer.dao.entity;

import com.oepraxxx.coupon.customer.dao.converter.CouponStatusConverter;
import com.oepraxxx.coupon.customer.dao.enums.CouponStatus;
import com.operaxxx.coupon.template.api.beans.CouponTemplateInfo;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.springframework.data.annotation.CreatedDate;

/**
 * @author hu kun
 * @date 2022/01/30
 */

public class Coupon {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  // 对应的模板ID - 不使用one to one映射
  // 不推荐使用级联查询的原因是为了防止滥用而导致的DB性能问题
  @Column(name = "template_id", nullable = false)
  private Long templateId;

  // 所有者的用户ID
  @Column(name = "user_id", nullable = false)
  private Long userId;

  // 冗余一个shop id方便查找
  @Column(name = "shop_id")
  private Long shopId;

  // 优惠券的使用/未使用状态
  @Column(name = "status", nullable = false)
  @Convert(converter = CouponStatusConverter.class)
  private CouponStatus status;

  // 被Transient标记的属性是不会被持久化的
  @Transient
  private CouponTemplateInfo templateInfo;

  // 获取时间自动生成
  @CreatedDate
  @Column(name = "created_time", nullable = false)
  private Date createdTime;

}
