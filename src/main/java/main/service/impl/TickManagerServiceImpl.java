package main.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import main.model.Tick;
import main.repository.TickRepository;
import main.service.ManagerTicks;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TickManagerServiceImpl implements ManagerTicks {

  private final TickRepository tickRepository;

  private List<Tick> ticks = new ArrayList<>();

  private BigDecimal priceLastTick = new BigDecimal(0);

  private final int SIZE_LIST_TICKS = 200;

  public void processingTick(BigDecimal price, Long time, Boolean flag) throws Exception {

    ticks.add(Tick.builder()
        .price(price)
        .timestamp(time)
        .trend(getTrendTick(price))
        .flagFrog(flag)
        .build());
    priceLastTick = price;

    if (ticks.size() > SIZE_LIST_TICKS) {
      addAllTick(getSortedListTicks());
      ticks.clear();
    }

  }

  public List<Tick> getSortedListTicks() {
    return ticks.stream().sorted(Comparator.comparingLong(Tick::getTimestamp)).toList();
  }

  public int getTrendTick(BigDecimal price) {
    return price.compareTo(priceLastTick) > 0 ? 1 : 0;
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
