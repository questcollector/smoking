package kr.co.miroirs.smoking.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import kr.co.miroirs.smoking.config.ApplicationConfig;
import kr.co.miroirs.smoking.config.WebMvcContextConfiguration;
import kr.co.miroirs.smoking.controller.DocumentDownloadController;
import kr.co.miroirs.smoking.controller.SmokingApiController;
import kr.co.miroirs.smoking.service.SmokingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ApplicationConfig.class, WebMvcContextConfiguration.class})
@WebAppConfiguration
public class ApiTest {

    @Mock
    SmokingService smokingServiceMock;
    @InjectMocks
    private SmokingApiController smokingApiController;
    @InjectMocks
    private DocumentDownloadController downloadController;
    
    private MockMvc mockMvc;
    
    @Before
    @Ignore
    public void setUpApi() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(smokingApiController)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }
    
    @Before
    public void setUpDownload() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(downloadController)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }

    @Test
    @Ignore
    public void testSmokingApiController() throws Exception {
        mockMvc.perform(get("/api/smokingArea"))
                .andExpect(status().isOk())
                .andDo(print());
    }   
    @Test
    public void testDownloadController() throws Exception {
        mockMvc.perform(get("/download?fileId=1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    
}
