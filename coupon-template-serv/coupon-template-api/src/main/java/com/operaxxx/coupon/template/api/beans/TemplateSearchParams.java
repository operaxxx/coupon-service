package com.operaxxx.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hu kun
 * @date 2021/12/30
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateSearchParams {
    private Long id;

    private String name;

    private String type;

    private Long shopId;

    private Boolean available;

    private int page;

    private int pageSize;
}
