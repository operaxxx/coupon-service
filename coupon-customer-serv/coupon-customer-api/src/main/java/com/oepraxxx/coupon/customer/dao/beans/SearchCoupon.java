package com.oepraxxx.coupon.customer.dao.beans;

import javax.validation.constraints.NotNull;
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
public class SearchCoupon {

  @NotNull
  private Long userId;
  private Long shopId;
  private Integer couponStatus;
}
