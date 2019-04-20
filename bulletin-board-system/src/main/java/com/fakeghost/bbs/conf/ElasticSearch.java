package com.fakeghost.bbs.conf;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.fakeghost.bbs.repo")
public class ElasticSearch {
    final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clustername}")
    private String EsClusterName;

    @Bean
    public Client esClient() throws Exception {

        log.info("cluster.name: " + EsClusterName);
        log.info("cluster.host: " + EsHost);
        log.info("cluster.port: " + EsPort);
        Settings esSettings = Settings.settingsBuilder()
                .put("client.transport.sniff",false)
                .put("cluster.name", EsClusterName)
                .build();

        //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
        return TransportClient.builder()
                .settings(esSettings)
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(esClient());
    }

}
