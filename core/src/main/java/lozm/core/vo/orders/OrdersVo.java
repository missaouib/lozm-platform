package lozm.core.vo.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lozm.core.code.OrderStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersVo {

    private Long id;
    private Long orderedPrice;
    private Long quantity;
    private Long userId;
    private Long itemId;
    private Long couponId;
    private String status;
    private int flag;

}
