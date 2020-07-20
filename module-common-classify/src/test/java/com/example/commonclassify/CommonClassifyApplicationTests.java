package com.example.commonclassify;

import com.example.commonclassify.manager.CommonClassifyManager;
import com.example.commonclassify.persistence.entity.CommonClassify;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
class CommonClassifyApplicationTests {

    @Autowired
    private CommonClassifyManager commonClassifyManager;

    @Test
    public void testCreate() {
        List<String> list = Arrays.asList("Spring", "SpringBoot", "SpringCloud");
        List<CommonClassify> result = list.stream().map(item -> {
            CommonClassify commonClassify = new CommonClassify();
            commonClassify.setScope("module-common-classify");
            commonClassify.setClassify("technology-stack");
            commonClassify.setName("Java");
            commonClassify.setValue(item);
            commonClassify.setPid("-1");
            return commonClassify;
        }).map(commonClassifyManager::add).collect(Collectors.toList());
        result.forEach(System.out::println);
    }

    @Test
    public void testQuery() {
        List<CommonClassify> dataList = commonClassifyManager.getAllByScopeAndClassifyAndName("module-common-classify", "technology-stack", "Java");
        List<String> result = dataList.stream().map(CommonClassify::getValue).collect(Collectors.toList());
        System.out.println(result);
    }

}
