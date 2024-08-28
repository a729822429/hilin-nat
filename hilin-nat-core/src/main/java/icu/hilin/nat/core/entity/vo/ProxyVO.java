package icu.hilin.nat.core.entity.vo;

import lombok.*;
import lombok.experimental.Accessors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class ProxyVO {

    /**
     * id
     */
    private long id;

    /**
     * 转发类型
     * 1 tcp
     * 2 udp
     */
    private int type;

    /**
     * 本地监听地址
     */
    private String localAddr;

    /**
     * 本地监听端口
     */
    private int localPort;

    /**
     * 远程地址
     */
    private String remoteAddr;
    /**
     * 远程端口
     */
    private int remotePort;

}
