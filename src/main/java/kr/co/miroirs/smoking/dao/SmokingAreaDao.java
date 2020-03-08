package kr.co.miroirs.smoking.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.miroirs.smoking.dto.BuildingCluster;
import kr.co.miroirs.smoking.dto.BuildingClusterResultSet;
import kr.co.miroirs.smoking.dto.SmokingArea;
import kr.co.miroirs.smoking.dto.SmokingAreaResultSet;
import kr.co.miroirs.smoking.query.Select;

@Repository
public class SmokingAreaDao {

    private NamedParameterJdbcTemplate jdbc;
    
    public SmokingAreaDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<SmokingArea> selectP015() {
        return jdbc.query(Select.P015, new SmokingAreaResultSet());
    }
    public List<SmokingArea> selectP100() {
        return jdbc.query(Select.P100, new SmokingAreaResultSet());
    }
    public List<BuildingCluster> selectBuildingCluster() {
        return jdbc.query(Select.BUILDING_CLUSTER, new BuildingClusterResultSet());
    }
}
