package kr.co.miroirs.smoking.service.impl;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.miroirs.smoking.dao.FileInfoDao;
import kr.co.miroirs.smoking.dto.FileInfo;
import kr.co.miroirs.smoking.service.DownloadService;

@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    FileInfoDao fileInfoDao;
    
    @Override
    public FileInfo getFileInfo(int fileId) {
        Map<String, Integer> fileInfoParamMap = Collections.singletonMap("fileId", fileId);
        return fileInfoDao.selectFileInfoById(fileInfoParamMap);
    }

}
