package org.nthdimenzion;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.nthdimenzion.Constants.APP_PACKAGE_ROOT;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 2/17/14
 * Time: 10:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class CheckForJUnitTestMethods {



    @Test
    public void collectAllJUnitTestMethods()throws Exception{

        Set<Method> methods = getJUnitTestMethods();
        for (Method method : methods){
            String[] splitMethodNameByCamelCase = method.getName().split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
            List<String> methodNameList = new ArrayList();
            for (String splitMethodNameByNumber : splitMethodNameByCamelCase){
                for (String methodNameSplitByNumber : splitMethodNameByNumber.split("(?<=[a-zA-Z])(?=[0-9])")) {
                    String methodName = methodNameSplitByNumber.replace("_"," \n");
                    methodNameList.add(methodName);
                }
            }

            String methodName = StringUtils.join(methodNameList, " ");
            System.out.println(methodName);
            storeMethodNamesInAFile(methodName);
            System.out.println();
        }
    }

    private void storeMethodNamesInAFile(String methodName){
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter( new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File("src\\test\\resources\\JUnitTestMethods.txt"),true), "utf-8")));
            printWriter.println(methodName);
            printWriter.println("\n\n\n");
        } catch (Exception ex) {
        } finally {
            try {
                printWriter.close();
            }
            catch (Exception ex) {

            }
        }
    }

    private Set<Method> getJUnitTestMethods() {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder().setUrls(
                        ClasspathHelper.forPackage(APP_PACKAGE_ROOT) ).setScanners(
                        new MethodAnnotationsScanner()));
        return reflections.getMethodsAnnotatedWith(Test.class);
    }
}