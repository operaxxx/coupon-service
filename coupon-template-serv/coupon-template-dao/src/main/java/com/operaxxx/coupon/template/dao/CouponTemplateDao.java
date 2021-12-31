package com.operaxxx.coupon.template.dao;

import com.operaxxx.coupon.template.dao.entity.CouponTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author hu kun
 * @date 2021/12/30
 */
public interface CouponTemplateDao extends JpaRepository<CouponTemplate, Long> {

    /**
     * 根据Shop ID查询出所有券模板
     */
    List<CouponTemplate> findAllByShopId(Long shopId);

    /**
     * 分页 id 查询
     */
    Page<CouponTemplate> findAllByIdIn(List<Long> id, Pageable page);

    // 根据shop ID + 可用状态查询店铺有多少券模板
    Integer countByShopIdAndAvailable(Long shopId, Boolean available);

    // 将优惠券设置为不可用
    @Modifying
    @Query("update CouponTemplate c set c.available = 0 where c.id = :id")
    int makeCouponUnavailable(@Param("id") Long id);
}
