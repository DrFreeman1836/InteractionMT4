package main.service;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ManagerChart {

  private Deque<Integer> arrayCountTick = new LinkedList<>();

  private final int SIZE_CHART = 3;

  public void addCount(int count) {

    int numberLastPosition = arrayCountTick.size();

    if (arrayCountTick.size() == SIZE_CHART) {
      arrayCountTick.removeLast();
    }
    arrayCountTick.addFirst(count);

  }

  public List<Integer> getArrayCount() {
    System.out.println("=====================");

    arrayCountTick.forEach(System.out::println);

    return (List<Integer>) arrayCountTick;

  }


}
