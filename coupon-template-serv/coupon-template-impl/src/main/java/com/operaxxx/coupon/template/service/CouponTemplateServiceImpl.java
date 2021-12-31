package com.operaxxx.coupon.template.service;

import com.operaxxx.coupon.template.api.enums.CouponType;
import com.operaxxx.coupon.template.converter.CouponTemplateConverter;
import com.operaxxx.coupon.template.dao.CouponTemplateDao;
import com.operaxxx.coupon.template.dao.entity.CouponTemplate;
import com.operaxxx.coupon.template.service.interfaces.CouponTemplateService;
import com.operaxxx.coupon.template.api.beans.CouponTemplateInfo;
import com.operaxxx.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.operaxxx.coupon.template.api.beans.TemplateSearchParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author hu kun
 * @date 2021/12/30
 */

@Slf4j
@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {

    private final CouponTemplateDao couponTemplateDao;

    @Autowired
    public CouponTemplateServiceImpl(CouponTemplateDao couponTemplateDao) {
        this.couponTemplateDao = couponTemplateDao;
    }

    /**
     * 创建优惠券模板
     */
    @Override
    public CouponTemplateInfo createTemplate(CouponTemplateInfo request) {
        // 单个门店最多可以创建100张优惠券模板
        if (request.getShopId() != null) {
            Integer count = couponTemplateDao.countByShopIdAndAvailable(request.getShopId(), true);
            if (count >= 100) {
                log.error("the totals of coupon template exceeds maximum number, current count {}", count);
                throw new UnsupportedOperationException("exceeded the maximum of coupon templates that you can create");
            }
        }

        // 创建优惠券
        CouponTemplate template = CouponTemplate.builder()
                .name(request.getName())
                .description(request.getDesc())
                .category(CouponType.convert(request.getType()))
                .available(true)
                .shopId(request.getShopId())
                .rule(request.getRule())
                .build();
        template = couponTemplateDao.save(template);
        return CouponTemplateConverter.convertToTemplateInfo(template);
    }

    /**
     * 克隆优惠券模板
     *
     * @param templateId 模板id
     */
    @Override
    public CouponTemplateInfo cloneTemplate(Long templateId) {
        log.info("cloning template id {}", templateId);
        CouponTemplate source = couponTemplateDao.findById(templateId)
                .orElseThrow(() -> new IllegalArgumentException("invalid template ID"));

        CouponTemplate target = new CouponTemplate();
        BeanUtils.copyProperties(source, target);

        target.setAvailable(true);
        target.setId(null);

        couponTemplateDao.save(target);
        return CouponTemplateConverter.convertToTemplateInfo(target);
    }

    /**
     * 模板查询（分页）
     *
     * @param request 请求参数
     */
    @Override
    public PagedCouponTemplateInfo search(TemplateSearchParams request) {
        CouponTemplate example = CouponTemplate.builder()
                .shopId(request.getShopId())
                .category(CouponType.convert(request.getType()))
                .available(request.getAvailable())
                .name(request.getName())
                .build();

        Pageable page = PageRequest.of(request.getPage(), request.getPageSize());

        Page<CouponTemplate> result = couponTemplateDao.findAll(Example.of(example), page);
        List<CouponTemplateInfo> couponTemplateInfos = result.stream()
                .map(CouponTemplateConverter::convertToTemplateInfo)
                .collect(Collectors.toList());

        return PagedCouponTemplateInfo.builder()
                .templates(couponTemplateInfos)
                .page(request.getPage())
                .total(result.getTotalElements())
                .build();
    }

    /**
     * 通过模板ID查询优惠券模板
     *
     * @param id 模板 id
     */
    @Override
    public CouponTemplateInfo loadTemplateInfo(Long id) {
        Optional<CouponTemplate> template = couponTemplateDao.findById(id);
        return template.map(CouponTemplateConverter::convertToTemplateInfo).orElse(null);
    }

    /**
     * 使优惠券模板无效
     *
     * @param id 模板 id
     */
    @Override
    public void deleteTemplate(Long id) {
        int rows = couponTemplateDao.makeCouponUnavailable(id);
        if (rows == 0) {
            throw new IllegalArgumentException("Template Not Found: " + id);
        }
    }

    /**
     * 批量查询
     *
     * @param ids 模板 id 集合
     * @return 返回 map, key 是模板 ID，value 是模板详情
     */
    @Override
    public Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids) {
        List<CouponTemplate> templates = couponTemplateDao.findAllById(ids);

        return templates.stream()
                .map(CouponTemplateConverter::convertToTemplateInfo)
                .collect(Collectors.toMap(CouponTemplateInfo::getId, Function.identity()));
    }
}
