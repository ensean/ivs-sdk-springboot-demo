package com.example.ivssdkspringbootrestdemo.contoller;

import com.example.ivssdkspringbootrestdemo.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.ivs.model.CreateChannelResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @RequestMapping(value = "/api/channel/{cid}", method = RequestMethod.GET)
    public Map<String,String> getChannel(@PathVariable("cid") String cid){
        Map<String, String> ret = new HashMap<>();
        ret.put("cid", cid);
        return ret;
    }

    @RequestMapping(value = "/api/channel", method = RequestMethod.POST)
    public Map<String, String> createChannel(@RequestBody Map<String, String> params){

        System.out.println(params);

        String cn = "default_channel_name";
        try{
            cn = params.get("cname");
            CreateChannelResponse response = channelService.createChannel(cn);
            Map<String, String> res = new HashMap<>();
            res.put("channelName", response.channel().name());
            res.put("channelArn", response.channel().arn());
            res.put("igUrl", response.channel().ingestEndpoint());
            res.put("playbackUrl", response.channel().playbackUrl());
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
