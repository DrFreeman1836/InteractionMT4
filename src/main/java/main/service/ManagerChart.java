package main.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ManagerChart {

  private List<Integer> countTick = new ArrayList<>();

  private final int SIZE_CHART = 10;

  public void addCount(int count) {
    System.out.println(count);
    int lastPosition = countTick.size();

    if (countTick.size() == SIZE_CHART) {
      countTick.remove(0);

    } else {
      countTick.add(count);
    }
  }

  public void getCount() {
    System.out.println("=====================");
    for (int i = 0; i < countTick.size(); i++) {
      System.out.println(countTick.get(i));
    }
  }


}
/**
 * подобрать коллекцию наверное стек
 */
