package com.github.study.transaction.elastic;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 *
 */
public class ProductSearch {
    private static final String host = "10.200.6.197";
    private static final int port = 9300;
    public static void main(String[] args) throws Exception{
        Settings settings = Settings.builder()
                .put("cluster.name","elasticsearch")
                .build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));

//        prepareDatas(client);
        executeSeacher(client);

        client.close();
    }

    /**
     * 准备数据
     * @param client
     */
    private static void prepareDatas(TransportClient client) throws Exception{
        client.prepareIndex("shop","product","1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name","book")
                        .field("price",77)
                        .field("author","zhangsan")
                        .field("country","china")
                        .endObject())
                .get();

        client.prepareIndex("shop","product","2")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name","java")
                        .field("price",22)
                        .field("author","lisi")
                        .field("country","us")
                        .endObject())
                .get();

        client.prepareIndex("shop","product","3")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name","web")
                        .field("price",55)
                        .field("author","zhaosi")
                        .field("country","china")
                        .endObject())
                .get();

        client.prepareIndex("shop","product","4")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name","pyhon")
                        .field("price",39)
                        .field("author","wangwu")
                        .field("country","helan")
                        .endObject())
                .get();

        client.prepareIndex("shop","product","5")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name","C")
                        .field("price",88)
                        .field("author","liuliu")
                        .field("country","china")
                        .endObject())
                .get();

    }
    /**
     * 条件查询
     * @param client
     */
    private static void executeSeacher(TransportClient client){
        SearchResponse response = client.prepareSearch("shop")
                .setTypes("product")
                .setQuery(QueryBuilders.matchQuery("country","china"))
                .setPostFilter(QueryBuilders.rangeQuery("price").from(70).to(90))
                .setFrom(0).setSize(1)
                .get();
        SearchHit[] searchHits = response.getHits().getHits();
        for(int i = 0;i < searchHits.length;i++){
            System.out.println(searchHits[i].getSourceAsString());
        }
    }
}
