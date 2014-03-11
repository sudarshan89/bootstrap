package org.nthdimenzion;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: sujit
 * Date: 8/28/13
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class MySqlScriptTiH2DBTransformationUtility {

    /****
     * Configure these two paths
     */
    private static final String SCRIPTS_SQL = "\\scripts\\bootstrap.sql";
    private static final String OUTPUT_PATH = "F:\\codebases\\bootstrap\\backend\\src\\test\\resources\\scripts\\bootstrap.sql";


    static final String SPACE = " ";
    static final String FOR_PRIMARY_KEY = ");" ;

    static ArrayList<String> showMyOutput = new ArrayList<String>() ;
    public static void main(String ar[]) throws FileNotFoundException {
        PrintWriter pw = null;

        ArrayList<String> myLine = new ArrayList();
        try {
            String sReadLine = null;

            ClassLoader bundleClassLoader = Thread.currentThread().getContextClassLoader();
            InputStream is = bundleClassLoader.getResourceAsStream(SCRIPTS_SQL);

            URL url = bundleClassLoader.getResource(SCRIPTS_SQL);

            System.out.println(" url " + url.getPath());

            BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));

            while ((sReadLine=br.readLine())!=null) {
             myLine.add(sReadLine);
             }

            for (int i=0;i<myLine.size();i++){
                String line = myLine.get(i);
                if(line.contains("ENGINE=InnoDB")){
                    myLine.set(i,");");
                }else if(line.contains("FOREIGN KEY")){
                    myLine.set(i,"");
                    String removeComma = myLine.get(i-1);
                    myLine.set(i-1,removeComma.replace("),",")"));
                }
            }
            br.close();
            is.close();

            pw = new PrintWriter(OUTPUT_PATH);
            pw.print("");

            for (String df : myLine) {
                System.out.println(df);
                pw.println(df);
            }
            pw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

     }
 }
