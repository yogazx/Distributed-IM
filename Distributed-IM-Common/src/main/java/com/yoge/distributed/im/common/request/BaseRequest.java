package com.yoge.distributed.im.common.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author geyoujia
 * @createTime 2020-01-16
 */
@Data
@ToString
public class BaseRequest {

    /**
     * 唯一请求号
     */
    private String reqNo;

    /**
     * 当前请求时间戳
     */
    private long timestamp;

    public BaseRequest() {
        this.setTimestamp(System.currentTimeMillis() / 1000);
    }
}
