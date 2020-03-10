package kr.co.miroirs.smoking.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class BuildingClusterResultSet implements ResultSetExtractor<List<BuildingCluster>> {

    @Override
    public List<BuildingCluster> extractData(ResultSet rs) throws SQLException, DataAccessException {
        
        List<BuildingCluster> buildingClusters = new ArrayList<>();
        
        while(rs.next()) {
            BuildingCluster buildingCluster = new BuildingCluster();
            buildingCluster.setId(rs.getInt("id"));
            
            String geom = rs.getString("the_geom");
            MultiPolygon geometry;
            
            try {
                geometry = (MultiPolygon) new WKTReader().read(geom);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            
            buildingCluster.setGeometry(geometry);
            buildingCluster.setCreateDate(rs.getString("OPERT_DE"));
            buildingCluster.setSigCode(rs.getString("SIG_CD"));
            buildingCluster.setCluster(rs.getInt("EQB_MAN_SN"));
            
            buildingClusters.add(buildingCluster);
        }
        
        return buildingClusters;
    }

}
