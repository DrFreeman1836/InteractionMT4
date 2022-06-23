package main.service.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import main.model.Tick;
import main.repository.TickRepository;
import main.service.ManagerTicks;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TickManagerServiceImpl implements ManagerTicks {

  private final TickRepository tickRepository;

  private List<Tick> Ticks = new ArrayList<>();

  private int priceLastTick;

  public int processingTick(Double price, Long time, Boolean flag){

    //
    /*
    ToDo реализовать логику сохранения тиков
     */

    return 0;
  }

  @Override
  public int addTick(Tick tick) {

    return 0;
  }

  @Override
  public void addAllTick(List<Tick> listTicks) {
    tickRepository.saveAll(listTicks);
  }

  @Override
  public Tick getLastTick() {
    return null;
  }


}
