package com.fakeghost.bbs;

import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ElasticSearchTest {
    final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ElasticsearchOperations es;

    @Test
    public void shouldPrintEsSettings(){
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            log.info(k + " = " + v);
        });
    }
}
