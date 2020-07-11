package com.example.accessrecord;

import com.example.accessrecord.manager.AccessRecordManager;
import com.example.accessrecord.objects.QueryAccessRecordParams;
import com.example.accessrecord.persistence.entity.AccessRecord;
import com.example.devutils.dep.Range;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
class AccessRecordApplicationTests {

    @Autowired
    private AccessRecordManager accessRecordManager;

    @Test
    public void testQuery() {
        QueryAccessRecordParams params = new QueryAccessRecordParams();
        params.setUserAgentLike("Window");
        params.setDuration(new Range<>(50, null));
        Page<AccessRecord> page = accessRecordManager.getAllByQueryParams(params);
        long totalElements = page.getTotalElements();
        page.getContent().forEach(System.out::println);
    }

}
