package main.service.impl;

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

  public int processingTick(Double price, Long time, Boolean flag){

    //

    return 0;
  }

  @Override
  public int addTick(Tick tick) {

    return 0;
  }

  @Override
  public void addAllTick(List<Tick> listTicks) {

  }

  @Override
  public Tick getLastTick() {
    return null;
  }


}
