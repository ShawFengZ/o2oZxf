package com.zxf.dao;

import com.zxf.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;


//告诉Junit spring配置文件的位置
@ContextConfiguration({"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AreaMapperTest {

    @Autowired
    AreaMapper areaMapper;

    @Test
    public void getList(){
        List<Area> areaList = areaMapper.getAreaList();
    }

    @Test
    public void deleteByPrimaryKey() {

    }

    @Test
    public void insert() {
        Area area = new Area();
        area.setAreaName("青海湖");
        area.setCreateTime(new Date());
        area.setLastEditTime(new Date());
        area.setPriority(1);
        areaMapper.insert(area);
    }

    @Test
    public void insertSelective() {
        Area area = new Area();
        area.setAreaName("北京");
        areaMapper.insertSelective(area);
    }

    @Test
    public void selectByPrimaryKey() {
        Area area = areaMapper.selectByPrimaryKey(1);
        System.out.println(area.toString());
    }

    @Test
    public void updateByPrimaryKeySelective() {
        Area area = areaMapper.selectByPrimaryKey(1);
        area.setAreaName("南京");
        areaMapper.updateByPrimaryKey(area);
    }

    @Test
    public void updateByPrimaryKey() {
    }
}