package kr.co.miroirs.smoking.service;

import java.util.List;

import kr.co.miroirs.smoking.dto.BuildingCluster;
import kr.co.miroirs.smoking.dto.SmokingArea;

public interface SmokingService {

    public List<SmokingArea> getSmokingAreaP015();
    public List<SmokingArea> getSmokingAreaP100();
    public List<BuildingCluster> getBuildingCluster();
}
