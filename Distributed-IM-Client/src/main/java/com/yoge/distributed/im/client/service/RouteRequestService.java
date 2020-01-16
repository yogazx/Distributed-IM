package com.yoge.distributed.im.client.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yoge.distributed.im.client.vo.req.LoginReqVO;
import com.yoge.distributed.im.client.vo.res.DistributedIMServerResVO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author geyoujia
 * @createTime 2020-01-16
 */
@Service
@Slf4j
public class RouteRequestService {

    @Value("${im.server.route.request.url}")
    private String serverRouteLoginUrl;

    @Autowired
    private OkHttpClient okHttpClient;

    /**
     * 获取服务器信息
     *
     * @param loginReqVO
     * @return 服务ip+port
     */
    public DistributedIMServerResVO.ServerInfo getIMServer(LoginReqVO loginReqVO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", loginReqVO.getUserId());
        jsonObject.put("username", loginReqVO.getUserName());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Request request = new Request.Builder()
                .url(serverRouteLoginUrl)
                .post(requestBody)
                .build();
        DistributedIMServerResVO distributedIMServerResVO = new DistributedIMServerResVO();
        ResponseBody responseBody = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            responseBody = response.body();
            String body = responseBody.string();
            distributedIMServerResVO = JSON.parseObject(body, DistributedIMServerResVO.class);
            // todo 重复失败
        } catch (IOException e) {
            log.error("获取服务器信息失败, {}", e.getMessage());
        } finally {
            responseBody.close();
        }
        return distributedIMServerResVO.getDataBody();
    }
}
