import java.math.BigDecimal;
import main.Application;
import main.service.impl.TickManagerServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class TestTickManagerServiceImpl {

    @Autowired
    TickManagerServiceImpl tickManagerService;

    @Before("")
    public void setUp() throws Exception{
        tickManagerService.processingTick(new BigDecimal("0.9567"), 100L, false);
        tickManagerService.processingTick(new BigDecimal("0.9568"), 101L, false);
        tickManagerService.processingTick(new BigDecimal("0.9569"), 102L, false);
        tickManagerService.processingTick(new BigDecimal("0.9577"), 103L, false);
        tickManagerService.processingTick(new BigDecimal("0.9587"), 104L, false);
        tickManagerService.processingTick(new BigDecimal("0.9597"), 105L, false);
        tickManagerService.processingTick(new BigDecimal("0.9567"), 106L, false);
        tickManagerService.processingTick(new BigDecimal("0.9557"), 107L, true);
    }

    @Test
    public void test(){

    }

}
