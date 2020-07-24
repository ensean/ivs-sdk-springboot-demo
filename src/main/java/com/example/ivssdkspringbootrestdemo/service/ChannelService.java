package com.example.ivssdkspringbootrestdemo.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ivs.IvsClient;
import software.amazon.awssdk.services.ivs.model.CreateChannelRequest;
import software.amazon.awssdk.services.ivs.model.CreateChannelResponse;

import java.util.HashMap;
import java.util.Properties;

@Service
public class ChannelService {

    public CreateChannelResponse createChannel(String channelName){

        Properties p = System.getProperties();
        p.put("aws.accessKeyId", "AKIAxxxxx");
        p.put("aws.secretAccessKey", "BAlOaPxxxxx");
        System.setProperties(p);
        SystemPropertyCredentialsProvider sysPopsProvider = SystemPropertyCredentialsProvider.create();

        Region region = Region.US_WEST_2;   // Oregon
        IvsClient ivsClient = IvsClient.builder().credentialsProvider(sysPopsProvider).region(region).build();
        // 存放标签
        HashMap<String, String> cTags = new HashMap<>();
        cTags.put("test", "yes");
        cTags.put("project", "vn");

        CreateChannelRequest createChannelRequest = CreateChannelRequest
                .builder()
                .latencyMode("LOW") // LOW or NORMAL，频道延迟
                .name(channelName) // 频道名称，自定义
                .type("STANDARD")   // BASIC or STANDARD，频道类型
                .tags(cTags)
                .build();
        CreateChannelResponse resp = ivsClient.createChannel(createChannelRequest);
        System.out.println(resp.toString());

        return resp;
    }

}
