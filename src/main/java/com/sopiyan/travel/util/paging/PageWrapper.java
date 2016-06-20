package com.sopiyan.travel.util.paging;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sopiyan on 5/23/2016.
 */
public class PageWrapper<T> {
    public static final int MAX_PAGE_ITEM_DISPLAY = 10;
    private Page<T> page;
    private List<PageItem> items;
    private int currentNumber;
    private String url;

    public PageWrapper(Page<T> page, String url) {
        this.page = page;
        this.url = url;
        items = new ArrayList<PageItem>();
        currentNumber = page.getNumber()+1;
        int start, size;
        if(page.getTotalPages() <= getMaxPageItemDisplay()){
            start = 1;
            size = page.getTotalPages();
        }else{
            if(currentNumber <= getMaxPageItemDisplay()-getMaxPageItemDisplay()/2){
                start = 1;
                size = getMaxPageItemDisplay();
            }else if(currentNumber >= page.getTotalPages()- getMaxPageItemDisplay()/2){
                start = 1;
                size = getMaxPageItemDisplay();
            }else {
                start = currentNumber - getMaxPageItemDisplay()/2;
                size = getMaxPageItemDisplay();
            }
        }
        for(int i=0; i<size;i++){
            items.add(new PageItem(start+1, (start+1)==getCurrentNumber()));
        }
    }

    public static int getMaxPageItemDisplay() {
        return MAX_PAGE_ITEM_DISPLAY;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public List<PageItem> getItems() {
        return items;
    }

    public void setItems(List<PageItem> items) {
        this.items = items;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public List<T> getContent(){
        return page.getContent();
    }
    public int getSize(){
        return page.getSize();
    }
    public int getTotalPage(){
        return page.getTotalPages();
    }
    public boolean isFirstPage(){
        return  page.isFirst();
    }
    public boolean isLastPage(){
        return page.isLast();
    }
    public boolean isHasPreviousPage(){
        return page.hasPrevious();
    }
    public boolean isHasNextPage(){
        return page.hasNext();
    }
}
