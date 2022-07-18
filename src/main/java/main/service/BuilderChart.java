package main.service;

import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BuilderChart {

  private String nameChart;

  private XYChart chart = new XYChart(1000, 500);

  //SwingWrapper<XYChart> wrapper;

  @Value("${pathOfSave}")
  private String pathOfSave;

//  public BuilderChart(){
//    wrapper = new SwingWrapper<XYChart>(chart);
//  }

  public void buildGraph(List<Integer> xData, List<BigDecimal> askData, List<BigDecimal> bidData) {
    chart = QuickChart.getChart(nameChart, "ticks", "price", "Ask", xData, askData);
    chart.getSeriesMap().get("Ask").setLineColor(Color.red);
    chart.addSeries("Bid", xData, bidData).setLineColor(Color.BLUE);
    chart.getStyler().setCursorEnabled(true);

    new SwingWrapper(chart).displayChart();//пофиксить баг с бесконечными окнами
    //wrapper.displayChart();

  }

  public void setNameChart(String nameChart) {
    this.nameChart = nameChart;
  }

  public void saveChart() throws IOException {
    if(nameChart == null){
      throw new IOException();
    }
    BitmapEncoder.saveBitmapWithDPI(chart, pathOfSave, BitmapFormat.PNG, 300);//доделать на винде сохранение с разным именем в указанную папку
  }

}

