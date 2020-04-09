package kr.co.miroirs.smoking.dao;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.miroirs.smoking.dto.FileInfo;


@Repository
public class FileInfoDao {
    
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<FileInfo> fileInfoRowMapper = 
            BeanPropertyRowMapper.newInstance(FileInfo.class);  
    
    public FileInfoDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public FileInfo selectFileInfoById(Map<String, Integer> fileInfoParamMap) {
        return jdbc.queryForObject(Select.SELECT_FILE_INFO, fileInfoParamMap, fileInfoRowMapper);
    }
}
