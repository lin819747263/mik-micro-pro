package com.mik.core.pojo;

import lombok.Data;

@Data
public class PageInput {
    private Integer pageNum;
    private Integer pageSize;

    public PageInput() {
    }

    public PageInput(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public static PageInput of(Integer pageNum, Integer pageSize){
        return new PageInput(pageNum, pageSize);
    }
}
