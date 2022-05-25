package NettyTest.entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;


@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
@ToString
public class RpcRequest {
    private String interfaceName;
    private String methodName;
}
