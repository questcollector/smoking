package kr.co.miroirs.smoking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.miroirs.smoking.dao.SmokingAreaDao;
import kr.co.miroirs.smoking.dto.BuildingCluster;
import kr.co.miroirs.smoking.dto.SmokingArea;
import kr.co.miroirs.smoking.service.SmokingService;

@Service
public class SmokingServiceImpl implements SmokingService{

    @Autowired
    SmokingAreaDao smokingAreaDao;

    @Override
    public List<SmokingArea> getSmokingAreaP015() {
        return smokingAreaDao.selectP015();
    }

    @Override
    public List<SmokingArea> getSmokingAreaP100() {
        return smokingAreaDao.selectP100();
    }

    @Override
    public List<BuildingCluster> getBuildingCluster() {
        return smokingAreaDao.selectBuildingCluster();
    }
}
