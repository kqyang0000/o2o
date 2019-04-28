package com.imooc.o2o.service;

import com.imooc.o2o.entity.Area;
import com.imooc.o2o.exceptions.AreaOperationException;

import java.util.List;

public interface AreaService {
    String AREALISTKEY = "arealist";

    List<Area> getAreaList() throws AreaOperationException;
}
