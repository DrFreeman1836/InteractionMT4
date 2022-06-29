package service;

import main.Application;
import main.service.ManagerChart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
public class TestManagerChart {

    @Autowired
    private ManagerChart managerChart;

    @Test
    public void addCountTest(){

        managerChart.addCount(2);
        managerChart.addCount(4);
        managerChart.addCount(6);
        managerChart.addCount(10);//

        List<Integer> expected = new ArrayList<>(List.of(4, 6, 10));
        List<Integer> result = managerChart.getArrayCount();
        assertEquals(expected, result);

        int expectedSize = 3;
        int resultSize = managerChart.getArrayCount().size();
        assertEquals(expectedSize, resultSize);

    }

}
