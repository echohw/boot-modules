package com.example.regionstore;

import com.example.devutils.dep.Charsets;
import com.example.devutils.utils.collection.ListUtils;
import com.example.regionstore.manager.RegionManager;
import com.example.regionstore.objects.QueryRegionParams;
import com.example.regionstore.persistence.entity.Region;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
class RegionApplicationTests {

    @Autowired
    private RegionManager regionManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testInitData() throws IOException {
        String sqlPath = "D:\\WorkSpace\\MyAll\\Resources\\Other\\中国区域数据\\cnarea20191031.sql";
        Path path = Paths.get(sqlPath);
        String prefix = "insert into `china_region`(`id`,`level`,`parent_code`,`area_code`,`zip_code`,`city_code`,`name`,`short_name`,`merger_name`,`pinyin`,`lng`,`lat`) values ";
        List<String> lines = Files.readAllLines(path, Charsets.UTF_8).stream()
            .filter(line -> line.startsWith("("))
            .map(line -> line.substring(0, line.length() - 1) + ",")
            .collect(Collectors.toList());
        AtomicInteger counter = new AtomicInteger(1);
        List<ArrayList<String>> grouping = ListUtils.grouping(lines, (f, s) -> counter.getAndIncrement() % 2000 != 0);
        for (ArrayList<String> lineList : grouping) {
            String join = String.join("", lineList);
            String sql = prefix + join.substring(0, join.length() - 1);
            System.out.println(sql);
            jdbcTemplate.execute(sql);
        }
    }

    @Test
    public void testQuery() {
        QueryRegionParams params = new QueryRegionParams();
        params.setPageSize(100);
        params.setNameLike("广州");
        Page<Region> page = regionManager.getAllByQueryParams(params);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        List<Region> content = page.getContent();
    }

}
