package main.service;

import java.io.IOException;
import main.repository.TickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerChart {

  private final BuilderChart chart;

  private int lastId = 1;

  private int count = 18;

  private final TickRepository tickRepository;

  @Autowired
  public ManagerChart(BuilderChart chart, TickRepository tickRepository) {
    this.chart = chart;
    this.tickRepository = tickRepository;
  }

  public void getSelection(String from, String to) {

    //

  }

  public void allSelection(){

    lastId = tickRepository.getIdNextFlag(lastId);
    System.out.println(lastId);//проверить что будет когда уже вернул максимальный

    //tickRepository.getLastPointFrog(lastId, count);

  }

  public void savePicture() throws IOException {
    chart.saveChart();
  }

}

