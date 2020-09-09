package coffee;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long orderId;
    private Long requestId;
    private String status;
    private Long menuId;
    private Long price;
    private Integer cnt;
    private String orderTime;
    private String orderMethod;

    @PostPersist
    public void onPostPersist(){
        Ordered ordered = new Ordered();
        BeanUtils.copyProperties(this, ordered);
        ordered.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        // OrderUpdated orderUpdated = new OrderUpdated();
        // BeanUtils.copyProperties(this, orderUpdated);
        // orderUpdated.publishAfterCommit();

        SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMdd HHmmss");
        Date today = new Date();
        String time = format.format(today);

        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        coffee.external.Cancelation cancelation = new coffee.external.Cancelation();
        // mappings goes here

        cancelation.setRequestId(orderCanceled.getRequestId());
        cancelation.setStatus("Canceled");
        cancelation.setCancelTime(time);

        OrderApplication.applicationContext.getBean(coffee.external.CancelationService.class)
            .makeCancel(cancelation);


    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
    public String getOrderMethod() {
        return orderMethod;
    }

    public void setOrderMethod(String orderMethod) {
        this.orderMethod = orderMethod;
    }




}
