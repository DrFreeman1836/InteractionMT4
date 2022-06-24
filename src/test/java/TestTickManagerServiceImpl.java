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
        tickManagerService.processingTick(0.9523, 100L, false);
    }

    @Test
    public void test(){

    }

}
