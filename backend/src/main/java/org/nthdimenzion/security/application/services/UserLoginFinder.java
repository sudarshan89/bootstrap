package org.nthdimenzion.security.application.services;

import com.google.common.collect.ImmutableMap;
import org.nthdimenzion.ddd.domain.annotations.Finder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: th Dimen
 * Date: 6/26/13
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
@Finder
public class UserLoginFinder {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public String advancedUserAttributesQuery = "select IS_ACCOUNT_NON_LOCKED as isAccountNonLocked from USER_LOGIN " +
            "where USERNAME = :username";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Map<String,Object> getAdvancedUserAttributes(String username){
        return namedParameterJdbcTemplate.queryForMap(advancedUserAttributesQuery, ImmutableMap.of("username",username));
    }
}
