package com.mark.javabean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author mark on 2017/3/13.
 */
public class ApplicationStarter {
    public static void main(String... args) {
        String[] seeds = {"http://hangzhou.tianyancha.com/search/oc21"};
        try {
            Document document = Jsoup.connect("http://hangzhou.tianyancha.com/search/oc21").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
