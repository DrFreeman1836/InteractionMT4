package main.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import main.model.Tick;
import main.repository.TickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerChart {

  private int lastId = 1;

  private final int count = 18;

  private final TickRepository tickRepository;

  private final BuilderChart builderChart;

  private final DateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

  private final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

  @Autowired
  public ManagerChart(TickRepository tickRepository, BuilderChart builderChart) {
    this.tickRepository = tickRepository;
    this.builderChart = builderChart;
  }

  public void getSelectionOnTime(String from, String to) throws ParseException {//14.07.2022 16:53:25

    List<Tick> data = tickRepository.getTicksFromToTime(getMsOnTime(from), getMsOnTime(to));
    List<Integer> xData = getXDate(data);
    List<BigDecimal> askData = data.stream().map(Tick::getPriceAsk).collect(Collectors.toList());
    List<BigDecimal> bidData = data.stream().map(Tick::getPriceBid).collect(Collectors.toList());
    builderChart.setNameChart(getNameChart(data));
    builderChart.buildGraph(xData, askData, bidData);

  }

  public void getAllSelection() {

    lastId = tickRepository.getNextFlag(lastId).isEmpty() ? lastId
        : tickRepository.getNextFlag(lastId).get();
    List<Tick> data = tickRepository.getLastPointFrog(lastId, count);
    List<Integer> xData = data.stream().map(Tick::getId).collect(Collectors.toList());
    List<BigDecimal> askData = data.stream().map(Tick::getPriceAsk).collect(Collectors.toList());
    List<BigDecimal> bidData = data.stream().map(Tick::getPriceBid).collect(Collectors.toList());
    builderChart.setNameChart(getNameChart(data));
    builderChart.buildGraph(xData, askData, bidData);

  }

  public void savePicture() throws IOException {
    builderChart.saveChart();
  }

  public void setLastId() {
    lastId = 1;
  }

  private List<Integer> getXDate(List<Tick> list){
    List<Integer> listXData = new ArrayList<>();
    for(int i = 0; i < list.size(); i++){
      listXData.add(i);
    }
    return listXData;
  }

  private String getNameChart(List<Tick> list) {
    try {
      Long start = list.get(0).getTimestamp();
      Long finish = list.get(list.size() - 1).getTimestamp();
      return getTimeOnMs(start) + " - " + getTimeOnMs(finish);
    } catch (RuntimeException e) {
      return "Н.Э.";
    }
  }

  private Long getMsOnTime(String time) throws ParseException {
    return FORMAT.parse(time).getTime();
  }

  private String getTimeOnMs(Long ms) {
    Date date = new Date(ms);
    return FORMATTER.format(date);
  }

}

