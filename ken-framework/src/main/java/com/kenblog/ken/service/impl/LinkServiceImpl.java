package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.constants.SystemConstants;
import com.kenblog.ken.domain.entity.Link;
import com.kenblog.ken.domain.vo.LinksVo;
import com.kenblog.ken.service.LinkService;
import com.kenblog.ken.mapper.LinkMapper;
import com.kenblog.ken.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 1037859047
* @description 针对表【sg_link(友链)】的数据库操作Service实现
* @createDate 2023-08-08 00:18:42
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
    implements LinkService{

    @Override
    public ResponseResult<String> getAllLink() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);

        List<Link> links = list(queryWrapper);
        List<LinksVo> linksVos = BeanCopyUtils.copyBeanList(links, LinksVo.class);

        return ResponseResult.okResult(linksVos);


    }
}




