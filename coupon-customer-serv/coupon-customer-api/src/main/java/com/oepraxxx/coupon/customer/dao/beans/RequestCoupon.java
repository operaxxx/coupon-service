package com.oepraxxx.coupon.customer.dao.beans;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hu kun
 * @date 2022/01/30
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCoupon {

  // 用户领券
  @NotNull
  private Long userId;

  // 券模板ID
  @NotNull
  private Long couponTemplateId;
}
