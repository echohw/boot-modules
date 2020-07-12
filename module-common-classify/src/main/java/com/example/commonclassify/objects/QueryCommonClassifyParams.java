package com.example.commonclassify.objects;

import lombok.Data;

/**
 * Created by AMe on 2020-07-12 15:12.
 */
@Data
public class QueryCommonClassifyParams {

    private int page = 0;
    private int pageSize = 10;

    private String scope;
    private String group;
    private String name;
    private String pid;
}
