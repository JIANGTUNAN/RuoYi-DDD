package com.ruoyi.ddd.domain.common.context;

import java.util.Map;

/**
 * HTTP 请求上下文
 * 存储 HTTP 请求的元数据，在请求进入时由 API 层创建。
 * 纯数据载体，不依赖任何 Servlet API。
 *
 * @param requestUri 请求 URI
 * @param method     请求方法（GET/POST/PUT/DELETE 等）
 * @param clientIp   客户端 IP 地址
 * @param token      身份令牌（未登录时为 null）
 * @param headers    请求头快照（不可变）
 * @param params     请求参数快照（不可变）
 * @author tooolan
 * @since 2026年4月9日
 */
public record HttpContext(String requestUri, String method, String clientIp, String token,
                          Map<String, String> headers, Map<String, String> params) {

    /**
     * 获取指定请求头
     *
     * @param name 请求头名称
     * @return 请求头值，不存在则返回 null
     */
    public String getHeader(String name) {
        return headers.get(name);
    }

    /**
     * 获取指定请求参数
     *
     * @param name 参数名称
     * @return 参数值，不存在则返回 null
     */
    public String getParam(String name) {
        return params.get(name);
    }

}
