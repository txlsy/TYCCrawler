package com.mark.javabean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {

    private Test() {

    }

    public static void main(String[] args) {
        getGoodsInfoFromWeb("6921734991539", "", "6910672061060");
    }

    private static void getGoodsInfoFromWeb(String gin, String line, String oldGin) {
        String host = "http://search.anccnet.com";
        String url = host + "/searchResult2.aspx?keyword=";
        String data = "";
        try {

            Document doc = Jsoup.connect(url + gin).timeout(60000)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                    .header("Origin", host)
                    .header("Referer", url + oldGin)
                    .get();

            Elements elements = doc.getElementsByClass("result");


            if (!elements.isEmpty()) {
                Element fistResult = elements.get(0);
                Elements res = fistResult.select(".p-info > dd");
                Elements suplis = fistResult.select(".p-supplier > dd");
                //规格，名称，生产企业，商标
                data = line + "," + res.get(2).text().replace(",", ";") + "," + res.get(1).text() + "," + suplis.get(1).text() + "," + suplis.get(0).text();
            } else {
                data = line + ",,,,";
            }

        } catch (Exception e) {
            e.printStackTrace();
            data = line + ",,,,";
        }

        System.out.println(data.replaceAll("\n", "").replaceAll("\r", ""));

    }
}
