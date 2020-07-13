package com.example.accessrecord.objects;

import com.example.devutils.dep.Range;
import lombok.Data;

/**
 * Created by AMe on 2020-07-09 18:18.
 */
@Data
public class QueryAccessRecordParams {

    private int page = 0;
    private int pageSize = 10;

    private String visitor;
    private String clientIp;
    private String userAgentLike;
    private String handlerClass;
    private String handlerMethod;
    private Range<Integer> duration = new Range<Integer>() {};
    private String scope;

}
