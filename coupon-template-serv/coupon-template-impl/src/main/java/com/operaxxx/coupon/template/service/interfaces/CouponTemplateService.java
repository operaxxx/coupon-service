package com.operaxxx.coupon.template.service.interfaces;

import com.operaxxx.coupon.template.api.beans.CouponTemplateInfo;
import com.operaxxx.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.operaxxx.coupon.template.api.beans.TemplateSearchParams;

import java.util.Collection;
import java.util.Map;

/**
 * @author hu kun
 * @date 2021/12/30
 */

public interface CouponTemplateService {

    /**
     * 创建优惠券模板
     */
    CouponTemplateInfo createTemplate(CouponTemplateInfo request);

    /**
     * 克隆优惠券模板
     */
    CouponTemplateInfo cloneTemplate(Long templateId);

    /**
     * 模板查询（分页）
     */
    PagedCouponTemplateInfo search(TemplateSearchParams request);

    /**
     * 通过模板ID查询优惠券模板
     */
    CouponTemplateInfo loadTemplateInfo(Long id);

    /**
     * 使优惠券模板无效
     */
    void deleteTemplate(Long id);

    /**
     * 批量查询
     *
     * @return key 是模板 ID，value 是模板详情
     */
    Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids);
}
