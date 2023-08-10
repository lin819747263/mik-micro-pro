package com.mik.db.entity;

import com.mybatisflex.annotation.Column;

import java.util.Date;

public class BaseEntity {
    @Column(onInsertValue = "now()")
    private Date createTime;
    @Column(onInsertValue = "now()" )
    private Date updateTime;
}
