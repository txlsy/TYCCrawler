package com.mark.javabean.crawl;

import java.util.List;
import java.util.Set;

/**
 * @author mark on 2017/3/30.
 */
public class Item {
    private Set<String> urls;
    private List<Item> children;

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public List<Item> getChildren() {
        return children;
    }

    public void setChildren(List<Item> children) {
        this.children = children;
    }
}
