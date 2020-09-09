package coffee;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long>{

    List<Order> findByRequestId(Long requestId);
}