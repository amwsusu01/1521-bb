package com.hanergy.reportForms.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Configuration
@PropertySource("classpath:/elasticsearch.properties")
public class ElasticsearchConfig {
    @Value("${elasticsearch.connect.name}")
    private String CONNECT_NAME;
    @Value("${elasticsearch.cluster.name}")
    private String CLUSTER_NAME;
    @Value("${elasticsearch.cluster.nodes}")
    private String CLUSTER_NODES;

    @Bean
    public TransportClient init() throws UnknownHostException {
        Settings settings = Settings.builder()
        .put("client.transport.sniff", false) //嗅探 默认每5s去更新下集群新的节点，加入新节点，去掉坏节点
        .put("cluster.name", CLUSTER_NAME)//集群名称
        .build();
        TransportClient client = new PreBuiltTransportClient(settings);
        String address ="";
        String port ="";
        if (CLUSTER_NODES.indexOf(",") > 0) {
            String[] nodes = CLUSTER_NODES.split(",");
            for (String node : nodes) {
                address = node.substring(0, node.indexOf(":"));
                port = node.substring(node.indexOf(":") + 1, node.length());
                client.addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName(address), Integer.parseInt(port))
                );
            }
        }else {
            address = CLUSTER_NODES.substring(0, CLUSTER_NODES.indexOf(":"));
            port = CLUSTER_NODES.substring(CLUSTER_NODES.indexOf(":") + 1, CLUSTER_NODES.length());
            client.addTransportAddress(
                    new InetSocketTransportAddress(InetAddress.getByName(address), Integer.parseInt(port))
            );
        }
        return client;
    }
}
