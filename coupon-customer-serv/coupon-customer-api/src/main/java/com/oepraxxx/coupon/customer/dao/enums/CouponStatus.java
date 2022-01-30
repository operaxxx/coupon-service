package com.oepraxxx.coupon.customer.dao.enums;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hu kun
 * @date 2022/01/30
 */

@Getter
@AllArgsConstructor
public enum CouponStatus {
  AVAILABLE("未使用", 1),
  USED("已用", 2),
  INACTIVE("已经注销", 3);

  private final String desc;
  private final Integer code;

  public static CouponStatus convert(Integer code) {
    if (code == null) {
      return null;
    }
    return Stream.of(values())
        .filter(bean -> bean.code.equals(code))
        .findAny()
        .orElse(null);
  }
}
