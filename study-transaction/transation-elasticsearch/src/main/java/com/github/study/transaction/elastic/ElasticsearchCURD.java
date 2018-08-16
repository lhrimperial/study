package com.github.study.transaction.elastic;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 *
 */
public class ElasticsearchCURD {

    private static final String host = "10.200.6.197";
    private static final int port = 9300;

    public static void main(String[] args) throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host),port));


        //add
//        addProduct(client);
        getProduct(client);
        updateProduct(client);
        getProduct(client);

        client.close();

    }

    private static void updateProduct(TransportClient client) throws Exception{
        UpdateResponse response = client.prepareUpdate("shop", "product", "1")
                .setDoc(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("price", 9.9)
                        .endObject()
                ).get();
        System.out.println(response.getResult());
    }

    private static void getProduct(TransportClient client) {
        GetResponse response = client.prepareGet("shop", "product", "1").get();
        System.out.println(response.getSourceAsString());
    }

    public static void addProduct(TransportClient client) throws Exception {
        IndexResponse response = client.prepareIndex("shop", "product", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "Thinking in java")
                        .field("price", "99")
                        .field("author", "jack")
                        .field("country", "us")
                        .endObject())
                .get();
        System.out.println(response);
    }
}
