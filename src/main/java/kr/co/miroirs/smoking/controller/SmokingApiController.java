package kr.co.miroirs.smoking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miroirs.smoking.api.object.SmokingAreaAll;
import kr.co.miroirs.smoking.api.object.SmokingAreaProperties;
import kr.co.miroirs.smoking.dto.SmokingArea;
import kr.co.miroirs.smoking.geojson.Feature;
import kr.co.miroirs.smoking.geojson.GeoJson;
import kr.co.miroirs.smoking.service.SmokingService;

@RestController
@RequestMapping("/api")
public class SmokingApiController {

    @Autowired
    SmokingService smokingService;
    
    @GetMapping("/smokingArea")
    public SmokingAreaAll getSmokingArea() {
        SmokingAreaAll smoking = new SmokingAreaAll();
        
        List<SmokingArea> p015 = smokingService.getSmokingAreaP015();
        List<SmokingArea> p100 = smokingService.getSmokingAreaP100();
        
        smoking.setP015(convertSmokingArea2GeoJson(p015));
        smoking.setP100(convertSmokingArea2GeoJson(p100));
        
        return smoking;
    }
    
    private GeoJson convertSmokingArea2GeoJson(List<SmokingArea> smokingAreas) {
        GeoJson geoJson = new GeoJson();
        
        geoJson.setType("FeatureCollection");
        
        List<Feature> features = new ArrayList<>();
        
        for(SmokingArea smokingArea: smokingAreas) {
            Feature feature = new Feature();
            
            feature.setType("Feature");
            feature.setGeometry(smokingArea.getGeometry());
            
            SmokingAreaProperties properties = new SmokingAreaProperties();
            
            properties.setId(smokingArea.getId());
            properties.setDayMorning(smokingArea.getDayMorning());
            properties.setDayNoon(smokingArea.getDayNoon());
            properties.setDayAfternoon(smokingArea.getDayAfternoon());
            properties.setDayEvening(smokingArea.getDayEvening());
            properties.setDayNight(smokingArea.getDayNight());
            properties.setEndMorning(smokingArea.getEndMorning());
            properties.setEndNoon(smokingArea.getEndNoon());
            properties.setEndAfternoon(smokingArea.getEndAfternoon());
            properties.setEndEvening(smokingArea.getEndEvening());
            properties.setEndNight(smokingArea.getEndNight());
            properties.setCount(smokingArea.getCount());
            properties.setValue(smokingArea.getValue());
            
            feature.setProperties(properties);
            
            features.add(feature);
            
        }
        geoJson.setFeatures(features);
        
        return geoJson;
    }
}
