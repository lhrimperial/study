package com.github.study;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */
public class HttpUtil {
    private static Logger logger = Logger.getLogger(com.caiwei.framework.util.http.HttpUtil.class);

    public HttpUtil() {
    }

    public static String sendPost(String path, String json) throws IOException, Exception {
        HttpURLConnection conn = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        StringBuilder resultStr = null;

        try {
            URL e = new URL(path);
            logger.info("request URL:" + e.toString());
            conn = (HttpURLConnection)e.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("accept", "*/*");
            printWriter = new PrintWriter(conn.getOutputStream());
            printWriter.write(json);
            printWriter.flush();
            int responseCode = conn.getResponseCode();
            if(responseCode != 200) {
                logger.info(path + " Error===" + responseCode);
            } else {
                logger.info(path + " Post Success!");
            }

            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            resultStr = new StringBuilder();

            String line;
            while((line = bufferedReader.readLine()) != null) {
                resultStr.append(line);
            }
        } catch (Exception var16) {
            var16.printStackTrace();
            throw new RuntimeException("请求异常");
        } finally {
            conn.disconnect();

            try {
                if(printWriter != null) {
                    printWriter.close();
                }

                if(bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

        return resultStr.toString();
    }

    public static String httpGet(String path) throws IOException {
        StringBuilder result = null;
        BufferedReader buffer = null;
        HttpURLConnection connet = null;

        try {
            URL e = new URL(path);
            connet = (HttpURLConnection)e.openConnection();
            connet.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connet.setRequestProperty("accept", "*/*");
            connet.connect();
            if(connet.getResponseCode() > 300) {
                logger.info(connet.getResponseCode() + " " + connet.getResponseMessage());
                throw new RuntimeException("请求异常");
            }

            buffer = new BufferedReader(new InputStreamReader(connet.getInputStream()));
            result = new StringBuilder();

            String line;
            while((line = buffer.readLine()) != null) {
                line = new String(line.getBytes(), "utf-8");
                result.append(line);
            }
        } catch (Exception var9) {
            throw new RuntimeException("请求异常");
        } finally {
            if(buffer != null) {
                buffer.close();
            }

            if(connet != null) {
                connet.disconnect();
            }

        }

        return result.toString();
    }

    public static void httpGetMedia(String url, String path, String fileName) {
        HttpURLConnection conn = null;
        InputStream inStream = null;
        FileOutputStream outStream = null;

        try {
            URL e = new URL(url);
            conn = (HttpURLConnection)e.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.connect();
            inStream = conn.getInputStream();
            File sf = new File(path);
            if(!sf.exists()) {
                sf.mkdirs();
            }

            outStream = new FileOutputStream(sf.getPath() + File.separator + fileName);
            byte[] buffer = new byte[1024];
            boolean len = false;

            int len1;
            while((len1 = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len1);
            }

            outStream.flush();
        } catch (Exception var18) {
            var18.printStackTrace();
        } finally {
            try {
                if(inStream != null) {
                    inStream.close();
                }

                if(outStream != null) {
                    outStream.close();
                }

                if(conn != null) {
                    conn.disconnect();
                }
            } catch (IOException var17) {
                var17.printStackTrace();
            }

        }

    }

    public static void writeStreamToFile(InputStream inStream, String path, String fileName) {
        FileOutputStream outStream = null;

        try {
            File e = new File(path);
            if(!e.exists()) {
                e.mkdirs();
            }

            outStream = new FileOutputStream(e.getPath() + File.separator + fileName);
            byte[] buffer = new byte[1024];
            boolean len = false;

            int len1;
            while((len1 = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len1);
            }

            outStream.flush();
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if(inStream != null) {
                    inStream.close();
                }

                if(outStream != null) {
                    outStream.close();
                }
            } catch (IOException var14) {
                var14.printStackTrace();
            }

        }

    }

    public static InputStream httpGetMedia(String url) {
        HttpURLConnection conn = null;
        InputStream inStream = null;

        try {
            try {
                URL e = new URL(url);
                conn = (HttpURLConnection)e.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.connect();
                inStream = conn.getInputStream();
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            return inStream;
        } finally {
            ;
        }
    }
}
