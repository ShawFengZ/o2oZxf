package com.zxf.service.impl;

import com.zxf.dao.AreaMapper;
import com.zxf.model.Area;
import com.zxf.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zxf
 * @date 2018/9/18 14:32
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> getAreaList() {
        return areaMapper.getAreaList();
    }
}
