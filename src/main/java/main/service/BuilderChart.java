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

  private XYChart chart = new XYChart(1000, 500);

  SwingWrapper<XYChart> wrapper;

  @Value("${pathOfSave}")
  private String pathOfSave;

  public BuilderChart() {
    chart.setXAxisTitle("ticks");
    chart.setYAxisTitle("price");
    chart.getStyler().setCursorEnabled(true);
    wrapper = new SwingWrapper<XYChart>(chart);
  }

  public void buildGraph(List<Integer> xData, List<BigDecimal> askData, List<BigDecimal> bidData) {
    if (chart.getSeriesMap().containsKey("Ask")) {
      chart.updateXYSeries("Ask", xData, askData, null);
      chart.updateXYSeries("Bid", xData, bidData, null);
    } else {
      chart.addSeries("Ask", xData, askData).setLineColor(Color.RED);
      chart.addSeries("Bid", xData, bidData).setLineColor(Color.BLUE);
    }

    wrapper.displayChart();

  }

  public void setNameChart(String nameChart) {
    chart.setTitle(nameChart);
  }

  public void saveChart() throws IOException {
    if (chart.getTitle() == null) {
      throw new IOException();
    }
    String path = pathOfSave + chart.getTitle().replaceAll(":", ".");
    System.out.println(path);//
    BitmapEncoder.saveBitmapWithDPI(chart, path, BitmapFormat.PNG, 600);
  }

}

