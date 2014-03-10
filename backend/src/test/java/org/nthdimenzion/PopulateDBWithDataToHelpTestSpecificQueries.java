package org.nthdimenzion;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 1/8/13
 * Time: 12:08 PM
 */
public class PopulateDBWithDataToHelpTestSpecificQueries {

    private static final String DBPATH = "jdbc:mysql://localhost:3306/macula";

    private static final String TEST_DATA_FILE_PATH = "src/test/resources/querybasedtestdata";


    public static void main(String[] args) throws Exception {
        JdbcDatabaseTester databaseTester = new JdbcDatabaseTester(com.mysql.jdbc.Driver.class.getName(),DBPATH, "root", "");
        File folder =new File(TEST_DATA_FILE_PATH);
        System.out.println(folder.getAbsolutePath());
        List<File> files = Arrays.asList(folder.listFiles());
        for (File file :files){
            System.out.println("Files picked up are : " + file);
            InputSource inputSource = new InputSource(new FileInputStream(file));
            FlatXmlProducer flatXmlProducer = new FlatXmlProducer(inputSource);
            IDataSet dataSet = new FlatXmlDataSet(flatXmlProducer);
            databaseTester.setDataSet(dataSet);
            databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
            databaseTester.onSetup();
        }
    }


}
