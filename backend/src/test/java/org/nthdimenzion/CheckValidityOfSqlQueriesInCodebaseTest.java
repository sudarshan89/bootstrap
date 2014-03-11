package org.nthdimenzion;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.ddd.domain.annotations.Finder;
import org.nthdimenzion.object.utils.UtilValidator;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.nthdimenzion.Constants.APP_PACKAGE_ROOT;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 30/7/13
 * Time: 8:51 AM
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class CheckValidityOfSqlQueriesInCodebaseTest {

    private static final String QUERY_SUFFIX = "Query";
    private static final int NUMBER_OF_THREADS = 8;
    @Autowired
    private DataSource dataSource;

    @Test
    public void checkValidityOfSqlQueries() throws Exception{
        final Set<Class<?>> finders = getFinders(APP_PACKAGE_ROOT);
        finders.addAll(getFinders("org.nthdimenzion"));
        final List<Query> queries = collectQueries(finders);

        long startTime = System.currentTimeMillis();

        ExecutorService executorService2 = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        List<List<Query>> partitionedQueries =  Lists.partition(queries, NUMBER_OF_THREADS);

        Collection<Callable<Void>> queryRunnables = Lists.newArrayList();

        for(List<Query> partionedQuery : partitionedQueries){
            Callable<Void> queriesRunner = new QueriesRunner(partionedQuery);
            queryRunnables.add(queriesRunner);
        }

        List<Future<Void>> results =  executorService2.invokeAll(queryRunnables);

        executorService2.shutdown();

        executorService2.awaitTermination(10l, TimeUnit.SECONDS);

        long timeTaken = System.currentTimeMillis() - startTime;

        System.out.println("TIme take  " + timeTaken);

        final List<Query> invalidQueries = filterInValidQueries(queries);
        if(UtilValidator.isNotEmpty(invalidQueries)){
            System.out.println("\n\n\n Queries have failed parsing");
            System.out.println(invalidQueries);
        }

        assertThat(invalidQueries, is((empty())));

    }

    private List<Query> filterInValidQueries(List<Query> queries) {
        List<Query> invalidQueries = Lists.newArrayList();
        for(Query query : queries){
            if(!query.isValidSql){
                invalidQueries.add(query);
            }
        }
        return invalidQueries;  //To change body of created methods use File | Settings | File Templates.

    }

    private Set<Class<?>> getFinders(String packageName){
        Reflections reflections = new Reflections(packageName);
        return reflections.getTypesAnnotatedWith(Finder.class);
    }

    private List<Query> collectQueries(Set<Class<?>> finders) throws IllegalAccessException, InstantiationException {
        List<Query> queries = Lists.newArrayList();
        for(Class finder : finders){
            Field[] queryFields = finder.getDeclaredFields();
            Object object = finder.newInstance();
            for(Field queryField : queryFields){
                if(queryField.getName().endsWith(QUERY_SUFFIX)){
                    Query query = new Query(queryField.get(object).toString(),queryField.getName(),finder.getName());
                    queries.add(query);
                }
            }
        }
        return queries;
    }

    public static boolean isSqlValid(String sqlQuery,DataSource dataSource){
        NamedParameterJdbcOperations namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        Map<String,?>  params = buildParams(sqlQuery);
        boolean isValidSql = true;
        try {
            namedParameterJdbcTemplate.queryForRowSet(sqlQuery, params);
        }catch (Exception ex){
            ex.printStackTrace();
            isValidSql = false;
        }
        return isValidSql;
    }

    public static Map<String,?> buildParams(String query){
        ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(query);
        Map<String,?> params = Maps.newHashMap();
        try{
            Method method = parsedSql.getClass().getDeclaredMethod("getParameterNames");
            method.setAccessible(true);
            List<String> paramKeys = (List<String>) method.invoke(parsedSql);
            for(String paramKey: paramKeys){
                params.put(paramKey,null);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return params;

    }

    @ToString
    private class Query{
        final String sqlQuery;
        final String fieldName;
        final String className;
        boolean isValidSql;

        private Query(String sqlQuery, String fieldName, String className) {
            this.sqlQuery = sqlQuery;
            this.fieldName = fieldName;
            this.className = className;
            this.isValidSql = true;
        }

        void markAsInvalidSql(){
            this.isValidSql = false;
        }
    }


    private class QueriesRunner implements Callable<Void > {
        private final List<Query> partionedQuery;

        public QueriesRunner(List<Query> partitionedQuery) {
            this.partionedQuery = partitionedQuery;
        }

        @Override
        public Void  call() {
            for(Query query : partionedQuery){
                boolean isValidSql = isSqlValid(query.sqlQuery,dataSource);
                if(!isValidSql){
                    query.markAsInvalidSql();
                }
            }
            return null;
        }
    }
}
