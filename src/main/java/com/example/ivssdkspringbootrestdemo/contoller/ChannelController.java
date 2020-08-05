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
            Map<String, String> resp = new HashMap<>();
            resp.put("code", 0);
            resp.put("msg", "event got");
            return resp;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
