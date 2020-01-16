package com.yoge.distributed.im.client.vo.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author geyoujia
 * @createTime 2020-01-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DistributedIMServerResVO {

    private String code;
    private String message;
    private String reqNo;
    private ServerInfo dataBody;

    @Data
    @ToString
    public static class ServerInfo {

        private String ip;
        private String serverPort;
        private Integer httpPort;
    }
}
