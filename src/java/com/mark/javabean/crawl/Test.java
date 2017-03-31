package com.mark.javabean.crawl;

/**
 * @author mark on 2017/3/14.
 */
public class Test {

    public static void main(String... args) {
        Crawler crawler = new Crawler();
        crawler.visit("https://www.zhihu.com/explore");
    }
}
