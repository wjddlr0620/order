package coffee;

import coffee.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    private OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRequested_Order(@Payload Requested requested){

        if(requested.isMe()){
            System.out.println("##### listener Order : " + requested.toJson());

            SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMdd HHmmss");
            Date today = new Date();
            String time = format.format(today);

            Order order = new Order();
            order.setRequestId(requested.getRequestId());
            order.setMenuId(requested.getMenuId());
            order.setPrice(requested.getPrice());
            order.setCnt(requested.getCnt());
            order.setOrderTime(time);
            order.setOrderMethod("Card");
            order.setStatus("Ordered");
            orderRepository.save(order);
        }
    }

}
