package kr.co.miroirs.smoking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miroirs.smoking.apiObject.SmokingAreaAll;
import kr.co.miroirs.smoking.dto.SmokingArea;
import kr.co.miroirs.smoking.service.SmokingService;

@RestController
@RequestMapping("/api")
public class SmokingApiController {

    @Autowired
    SmokingService smokingService;
    
    @GetMapping("/smokingArea")
    public SmokingAreaAll getSmokingArea() {
        SmokingAreaAll smoking = new SmokingAreaAll();
        
        List<SmokingArea> P015 = smokingService.getSmokingAreaP015();
        List<SmokingArea> P100 = smokingService.getSmokingAreaP100();
        
        smoking.setP015(P015);
        smoking.setP100(P100);
        
        return smoking;
    }
}
