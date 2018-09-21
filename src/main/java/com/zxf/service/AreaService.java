package com.zxf.service;

import com.zxf.entity.Area;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zxf
 * @date 2018/9/18 14:31
 */
public interface AreaService {

    List<Area> getAreaList();
}
