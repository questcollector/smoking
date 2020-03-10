package kr.co.miroirs.smoking.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class SmokingAreaResultSet implements ResultSetExtractor<List<SmokingArea>> {

    @Override
    public List<SmokingArea> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<SmokingArea> smokingAreas = new ArrayList<>();
        
        while(rs.next()) {
            SmokingArea smokingArea = new SmokingArea();
            smokingArea.setId(rs.getInt("id"));         
            
            String geom = rs.getString("geom");
            Geometry geometry;
            
            try {
                geometry = new WKTReader().read(geom);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            
            smokingArea.setGeometry((Point) geometry);
            smokingArea.setCount(rs.getInt("count"));
            smokingArea.setValue(rs.getDouble("value"));
            smokingArea.setDayMorning(rs.getInt("dayMorning"));
            smokingArea.setDayNoon(rs.getInt("dayNoon"));
            smokingArea.setDayAfternoon(rs.getInt("dayAfternoon"));
            smokingArea.setDayEvening(rs.getInt("dayEvening"));
            smokingArea.setDayNight(rs.getInt("dayNight"));
            smokingArea.setEndMorning(rs.getInt("endMorning"));
            smokingArea.setEndNoon(rs.getInt("endNoon"));
            smokingArea.setEndAfternoon(rs.getInt("endAfternoon"));
            smokingArea.setEndEvening(rs.getInt("endEvening"));
            smokingArea.setEndNight(rs.getInt("endNight"));
            
            smokingAreas.add(smokingArea);
        }
        
        return smokingAreas;
    }

}
