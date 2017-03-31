package com.mark.javabean.crawl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author mark on 2017/3/13.
 */
public class Crawler {

    private List<Item> items;

    public void visit(String target){
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet post = new HttpGet(target);
            HttpResponse response = client.execute(post);


            Document document = Jsoup.parse(response.getEntity().getContent(), "utf-8", target);
            String text = document.text();
            System.out.println(text.replace(" ","\r\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
