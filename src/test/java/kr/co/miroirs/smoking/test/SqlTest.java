package kr.co.miroirs.smoking.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.miroirs.smoking.config.ApplicationConfig;
import kr.co.miroirs.smoking.dto.BuildingCluster;
import kr.co.miroirs.smoking.dto.FileInfo;
import kr.co.miroirs.smoking.dto.SmokingArea;
import kr.co.miroirs.smoking.security.config.SecurityConfig;
import kr.co.miroirs.smoking.security.dto.UserDomain;
import kr.co.miroirs.smoking.security.service.UserService;
import kr.co.miroirs.smoking.service.DownloadService;
import kr.co.miroirs.smoking.service.SmokingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ApplicationConfig.class, SecurityConfig.class})
public class SqlTest {

    @Autowired
    private SmokingService smokingService;
    @Autowired
    private UserService userService;
    @Autowired
    private DownloadService downloadService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Test
    @Ignore
    public void getSmokingAreaP015Test() {
        try {
            List<SmokingArea> p015 = smokingService.getSmokingAreaP015();
            List<SmokingArea> p100 = smokingService.getSmokingAreaP100();
            logger.debug("{}", p015);
            assertNotNull(p100);
        } catch(Exception e) {
            logger.debug("{}: {}", e.getCause(), e.getMessage());
        }
        
    }
    
    @Test
    @Ignore
    public void polygonTest() {        
        try {
            List<BuildingCluster> buildingClusters = smokingService.getBuildingCluster();
            assertNotNull(buildingClusters);
            logger.debug("{}", buildingClusters.get(0));            
            
        } catch(Exception e) {
            logger.debug("{}: {}", e.getCause(), e.getMessage());
        }
        
        
    }
    
    @Test
    @Ignore
    public void userTest() {
        UserDomain user = (UserDomain) userService.loadUserByUsername("ADMIN");
        logger.debug("id: {}, password: {}", user.getUsername(), user.getPassword());
        assertEquals("ADMIN", user.getUsername());
    }
    
    @Test
    public void fileInfoTest() {
        FileInfo fileInfo = downloadService.getFileInfo(1);
        logger.debug("{}", fileInfo);
        assertNotNull(fileInfo);
    }
    
    
}
