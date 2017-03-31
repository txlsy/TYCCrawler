package com.mark.javabean.crawl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author mark on 2017/3/30.
 */
public class FictionReader {
    public void read() {
        String file = "reading.properties";

        String base = "http://m.biqudao.com/bqge7946/";
        String prev = "http://m.biqudao.com/bqge7946/4377586.html";
        String next = "http://m.biqudao.com/bqge7946/4377587.html";

        Properties properties = new Properties();

        try {
            InputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);

            base = properties.getProperty("base");
            prev = properties.getProperty("prev");
            next = properties.getProperty("next");

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            CloseableHttpClient temp = HttpClientBuilder.create().build();

            HttpGet get = new HttpGet(prev);
            CloseableHttpResponse response = temp.execute(get);

            Document document = Jsoup.parse(response.getEntity().getContent(), "utf-8", base);
            String text = document.text();
            System.out.println(text.replace(" ", "\r\n"));
            response.close();
            temp.close();

            while (true) {
                CloseableHttpClient client = HttpClientBuilder.create().build();
                get = new HttpGet(next);
                response = client.execute(get);

                document = Jsoup.parse(response.getEntity().getContent(), "utf-8", base);
                text = document.text();
                System.out.println(text.replace(" ", "\r\n"));
                response.close();
                client.close();

                prev = next;
                next = base + document.select("#pb_next").attr("href");

                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                if (!command.equals("next")){

                    properties.setProperty("prev", prev);
                    properties.setProperty("next", next);
                    OutputStream outputStream = new FileOutputStream(file);
                    properties.store(outputStream,null);
                    outputStream.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) throws Exception {
        FictionReader reader = new FictionReader();
        reader.read();
    }
}
