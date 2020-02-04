package kr.co.miroirs.smoking.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.miroirs.smoking.config.ApplicationConfig;
import kr.co.miroirs.smoking.dto.SmokingArea;
import kr.co.miroirs.smoking.service.SmokingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ApplicationConfig.class})
public class SqlTest {

    @Autowired
    private SmokingService smokingService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Test
    public void getSmokingAreaP015Test() {
        try {
            List<SmokingArea> p015 = smokingService.getSmokingAreaP015();
            List<SmokingArea> p100 = smokingService.getSmokingAreaP100();
            PGobject pgo = p015.get(0).getGeom();
            new PGgeometry();
            Geometry g = PGgeometry.geomFromString(pgo.getValue());
            
            System.out.println(g.getValue());
            System.out.println(g.getTypeString());
            System.out.println(g.getType());
            System.out.println(g.getPoint(0));
            System.out.println(g.getSrid());
            System.out.println(Geometry.GEOMETRYCOLLECTION);
            System.out.println(Geometry.LINEARRING);
            System.out.println(Geometry.LINESTRING);
            System.out.println(Geometry.MULTILINESTRING);
            System.out.println(Geometry.MULTIPOINT);
            System.out.println(Geometry.MULTIPOLYGON);
            System.out.println(Geometry.POINT);
            System.out.println(Geometry.POLYGON);
            
            //System.out.println(p100);
            assertNotNull(p100);
        } catch(Exception e) {
            logger.debug("{}: {}", e.getCause(), e.getMessage());
        }
        
    }
    
    
}
