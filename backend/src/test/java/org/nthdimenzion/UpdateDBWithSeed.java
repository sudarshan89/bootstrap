package org.nthdimenzion;

import com.google.common.collect.ImmutableList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UpdateDBWithSeed {

    /********************* User update section start ****************/
    // The order is very important, add new seed files if required
    List<File> seeds = ImmutableList.of(new File("src\\main\\resources\\com\\macula\\scripts\\structure.sql"),
            new File("src\\main\\resources\\com\\macula\\scripts\\seed.sql"),
            new File("src\\main\\resources\\com\\macula\\scripts\\institutes_data.sql"),
            new File("src\\main\\resources\\com\\macula\\scripts\\specilaisation.sql"),
            new File("src\\main\\resources\\com\\macula\\scripts\\chapterTopicSeed.sql"));
    /********************* User update section end****************/

    public void execute(ApplicationContext context) throws IOException {
        DataSource dataSource = context.getBean("dataSourceRef",DataSource.class);
        JdbcTemplate template = new JdbcTemplate(dataSource);



        for(File seed : seeds){
            System.out.println(seed.getName());
            JdbcTestUtils.executeSqlScript(template,new FileSystemResource(seed),true);
        }

    }

	public static void main(String[] args) throws IOException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("dataSourceContextForInsertingSeedData.xml");

        UpdateDBWithSeed p = context.getBean(UpdateDBWithSeed.class);

        p.execute(context);

        System.out.println(context);




	}


}