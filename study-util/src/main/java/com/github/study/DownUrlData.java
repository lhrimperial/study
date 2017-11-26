package com.github.study;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author longhr
 * @version 2017/11/24 0024
 */
public class DownUrlData {

    public static void main(String[] args){
        String url = "https://m.lvmama.com/tour/localplay?keyword=%E6%BC%94%E5%87%BA&homeSearch=13";
        down(url);
    }

    public static Document down(String url){
        Document doc = null;
        DefaultHttpClient httpClient=new DefaultHttpClient();
        Util.println("DownLoad:"+url);
        HttpGet get=new HttpGet(url);
        HttpResponse response;
        try {
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            doc = Jsoup.parse(entity.getContent(), "utf-8","");

            Elements doc1 = null;//doc.getElementsByClass("searchList");//doc.getElementsByTag("article");
            Elements doc2 = null;
            String str = "localplay_searchListUl";
            for (int i = 1; i <= 17; i++) {
                doc1 = doc.getElementsByAttributeValue("id", str + 1);
            }


            //释放资源
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭连接
        httpClient.getConnectionManager().shutdown();
        return doc;
    }
}
