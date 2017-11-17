package com.github.study.tools.filecut;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author longhr
 * @version 2017/11/15 0015
 */
public class TestIO {
    private long lineNum = 0;
    private String path = "";
    private String searchStr = "";
    private String regexStr = "";

    public String getRegexStr() {
        return regexStr;
    }

    public void setRegexStr(String regexStr) {
        this.regexStr = regexStr;
    }

    public void setPath(String value) {
        path = value;
    }

    public String getPath() {
        return path;
    }

    public void setSearchStr(String value) {
        searchStr = value;
    }

    public String getSearchStr() {
        return searchStr;
    }

    /**
     * Java search by index
     */
    public void start(int model) {
        if (null == path || path.length() < 1)
            return;
        try {
            long startMili = System.currentTimeMillis();
            System.out.println("Start search \"" + searchStr + "\" in file: " + path);
            File file = new File(path);
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"));
            String line = "";
            lineNum = 0;
            switch (model) {
                case 1 :
                    while ((line = reader.readLine()) != null) {
                        lineNum++;
                        String rs = this.searchStr(line, searchStr);
                        if (rs.length() > 0) {
                            System.out.println("Find in Line[" + lineNum + "], index: " + rs);
                        }
                    }
                case 2 :
                    Pattern p = Pattern.compile(regexStr);
                    while ((line = reader.readLine()) != null) {
                        lineNum++;
                        String rs = this.searchRexStr(line, p);
                        if (rs.length() > 0) {
                            System.out.println("Find in Line[" + lineNum + "], index: " + rs);
                        }
                    }
            }

            System.out.println("Finished!");
            long endMili = System.currentTimeMillis();
            System.out.println("Total times: " + (endMili - startMili) + " ms");
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Call shell command to search
     */
    public void startByShell() {
        try {
            long startMili = System.currentTimeMillis();
            System.out.println("Start search \"" + searchStr + "\" in file: " + path + " by shell");
            String[] cmd = {"/bin/sh", "-c", "grep " + searchStr + " " + path + " -n "};
            Runtime run = Runtime.getRuntime();
            Process p = run.exec(cmd);
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                String rs = this.searchStr(line.substring(line.indexOf(':') + 1), searchStr);
                if (rs.length() > 0) {
                    String linebyshell = line.substring(0, line.indexOf(':'));
                    //System.out.println("Find in Line["+linebyshell+"], index: "+rs);
                }
            }
            System.out.println("Finished!");
            long endMili = System.currentTimeMillis();
            System.out.println("Total times: " + (endMili - startMili) + " ms");
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String searchRexStr(String src, Pattern p) {
        String result = "";
        Matcher matcher = p.matcher(src);
        if (matcher.matches()) {
            result = matcher.start() + "";
        }
        return result;
    }

    public String searchStr(String src, String value) {
        String result = "";
        int index = src.indexOf(value, 0);
        while (index > -1) {
            result += index + ",";
            index = src.indexOf(value, index + value.length());
        }
        return result;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String prefix = "catalina.out-2017-11-14-";
        String[] suffix = {"149", "159", "198", "218", "141", "151", "161", "171", "233"};

        String file = "C:\\Users\\Administrator\\Desktop\\splitlog\\catalina.out-2017-11-14-159";
        TestIO test = new TestIO();
        if (args.length > 0)
            test.setPath(args[0]);
        else
            test.setPath(file);
        if (args.length > 1)
            test.setSearchStr(args[1]);
        else
            test.setSearchStr("9c04621f2c9a3f54bec29d0c2bc96850");
        //index
//        test.start(1);
        //regex
        test.setRegexStr("^9c04621f2c9a3f54bec29d0c2bc96850$");
        test.start(2);
//        test.startByShell();
    }
}
