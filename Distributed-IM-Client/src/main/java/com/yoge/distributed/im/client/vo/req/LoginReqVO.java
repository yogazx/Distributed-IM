package com.yoge.distributed.im.client.vo.req;

import com.yoge.distributed.im.common.request.BaseRequest;
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
public class LoginReqVO extends BaseRequest {

    private Long userId;

    private String userName;

}
