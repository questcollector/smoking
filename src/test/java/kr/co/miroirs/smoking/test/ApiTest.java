package kr.co.miroirs.smoking.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
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
import kr.co.miroirs.smoking.service.DownloadService;
import kr.co.miroirs.smoking.service.SmokingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ApplicationConfig.class, WebMvcContextConfiguration.class})
@WebAppConfiguration
public class ApiTest {

    @Mock
    SmokingService smokingServiceMock;
    @Mock
    DownloadService downloadServiceMock;
    
    @InjectMocks
    private SmokingApiController smokingApiController;
    @InjectMocks
    private DocumentDownloadController downloadController;
    
    private MockMvc mockMvcApi;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvcApi = MockMvcBuilders
                .standaloneSetup(smokingApiController)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }

    @Test
    public void testSmokingApiController() throws Exception {
        mockMvcApi.perform(get("/api/smokingArea"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    
}
