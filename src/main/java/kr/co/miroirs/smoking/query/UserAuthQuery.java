package kr.co.miroirs.smoking.query;

public class UserAuthQuery {

    private UserAuthQuery() {
        throw new IllegalStateException("Query class");
    }
    
    public static final String SELECT_USER_AUTH = "SELECT u.username AS username, "
            + "u.password AS password, u.enabled AS enabled, a.authority AS authority "
            + "FROM users AS u "
            + "INNER JOIN authorities AS a ON u.username = a.username "
            + "WHERE u.username = :username";
}
