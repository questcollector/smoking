package kr.co.miroirs.smoking.security.dao;

import static kr.co.miroirs.smoking.query.UserAuthQuery.SELECT_USER_AUTH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.miroirs.smoking.security.dto.UserDomain;

@Repository
public class UserAuthDao {
    
    private NamedParameterJdbcTemplate jdbc;
    
    public UserAuthDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public UserDomain selectUserDomain(Map<String, String> userParamMap) {

        return jdbc.queryForObject(SELECT_USER_AUTH, userParamMap, new RowMapper<UserDomain>() {

            @Override
            public UserDomain mapRow(ResultSet rs, int rowNum) throws SQLException {
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean enabled = rs.getBoolean("enabled");
                String authority = rs.getString("authority");
                return new UserDomain(username, password, authority, enabled);
            }
            
        });
    }
}
