package kr.co.miroirs.smoking.security.dao;

import static kr.co.miroirs.smoking.security.dao.UserAuthQuery.DELETE_AUTHORITY;
import static kr.co.miroirs.smoking.security.dao.UserAuthQuery.DELETE_USER;
import static kr.co.miroirs.smoking.security.dao.UserAuthQuery.SELECT_USER_AUTH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.co.miroirs.smoking.security.dto.UserDomain;

@Repository
public class UserAuthDao {
    
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert userInsert;
    private SimpleJdbcInsert authorityInsert;
    
    public UserAuthDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.userInsert = new SimpleJdbcInsert(dataSource)
                                .withTableName("users");
        this.authorityInsert = new SimpleJdbcInsert(dataSource)
                                    .withTableName("authorities");
        
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
    
    public int insertUser(UserDomain userDomain) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(userDomain);
        return userInsert.execute(params);
    }
    
    public int insertAuthority(UserDomain userDomain) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(userDomain);
        return authorityInsert.execute(params);
    }
    
    public int deleteUser(Map<String, Integer> userDeleteParamMap) {
        // Map<String, Integer> userDeleteParamMap = Collections.singletonMap("id", id);
        return jdbc.update(DELETE_USER, userDeleteParamMap);
    }
    public int deleteAuthority(Map<String, Integer> authorityDeleteParamMap) {
        // Map<String, Integer> authorityDeleteParamMap = Collections.singletonMap("id", id);
        return jdbc.update(DELETE_AUTHORITY, authorityDeleteParamMap);
    }
}
