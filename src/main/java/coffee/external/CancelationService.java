
package coffee.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

// @FeignClient(name="make", url="http://make:8080")
@FeignClient(name="make", url="${api.url.make}")
public interface CancelationService {

    @RequestMapping(method= RequestMethod.POST, path="/cancelations")
    public void makeCancel(@RequestBody Cancelation cancelation);

}