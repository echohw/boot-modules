package com.example.regionstore.objects;

import com.example.devutils.dep.Range;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Created by AMe on 2020-07-09 18:18.
 */
@Data
public class QueryRegionParams {

    private int page = 0;
    private int pageSize = 10;

    private Byte level;
    private Long parentCode;
    private String zipCode;
    private String cityCode;
    private String name;
    private String nameLike;
    private String shortName;
    private String shortNameLike;
    private String pinyin;
    private String pinyinLike;
    private Range<BigDecimal> longitude = new Range<BigDecimal>() {};
    private Range<BigDecimal> latitude = new Range<BigDecimal>() {};
}
