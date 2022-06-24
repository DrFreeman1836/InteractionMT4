package main.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
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

  private List<Tick> ticks = new ArrayList<>();

  private Double priceLastTick;

  public void processingTick(Double price, Long time, Boolean flag) throws Exception{

    ticks.add(Tick.builder()
            .price(price)
            .timestamp(time)
            .trend(getTrendTick(price))
            .flagFrog(flag)
            .build());
    priceLastTick = price;

    if(ticks.size() > 10){
      addAllTick(ticks);
      ticks.clear();
    }

  }

  public List<Tick> getSortedListTicks(){
    return ticks.stream().sorted(Comparator.comparingLong(Tick::getTimestamp)).toList();
  }

  public int getTrendTick(Double price){
    return price > priceLastTick ? 1 : 0;
  }

  @Override
  public int addTick(Tick tick) {
    return tickRepository.save(tick).getId();
  }

  @Override
  public void addAllTick(List<Tick> listTicks) {
    tickRepository.saveAll(listTicks);
  }

  @Override
  public Tick getLastTick() {
    return tickRepository.findAll().stream().max(Comparator.comparingLong(Tick::getTimestamp)).get();
  }


}
