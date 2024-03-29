package main.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import lombok.RequiredArgsConstructor;
import main.service.ManagerChart;
import main.service.impl.TickManagerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mt")
@RequiredArgsConstructor
public class ControllerMt {

  private final TickManagerServiceImpl tickManagerService;
  private final ManagerChart managerChart;

  @PostMapping()
  public ResponseEntity<?> addTick(
      @RequestParam(name = "priceAsk") BigDecimal priceAsk,
      @RequestParam(name = "priceBid") BigDecimal priceBid,
      @RequestParam(name = "flag", required = false, defaultValue = "false") Boolean flag) {

    Long time = System.currentTimeMillis();

    try {

      tickManagerService.processingTick(priceAsk, priceBid, time, flag);
      return ResponseEntity.ok().build();

    } catch (Exception ex) {
      ex.printStackTrace();
      return ResponseEntity.status(500).build();
    }

  }

  @GetMapping()
  public ResponseEntity<?> createChart(
      @RequestParam(name = "from", required = false) String from,
      @RequestParam(name = "to", required = false) String to) {

    if (from == null && to == null) {
      managerChart.getAllSelection();
      return ResponseEntity.ok().build();
    }
    if (from != null && to != null) {
      try {
        managerChart.getSelectionOnTime(from, to);
        return ResponseEntity.ok().build();
      } catch (ParseException e) {
        return ResponseEntity.status(500).body("Невалидный формат даты");
      }
    }
    return ResponseEntity.status(500).body("Неверные параметры");
  }

  @GetMapping("/history")
  public ResponseEntity<?> historyTick(
      @RequestParam(name = "id") Integer id,
      @RequestParam(name = "tolerance") Integer tolerance) {
    try {
      managerChart.getSelectionOnTick(id, tolerance);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(500).body(e.toString());
    }

  }

  @PostMapping("/first")
  public ResponseEntity<?> atFirst() {
    managerChart.setLastId();
    return ResponseEntity.ok().build();
  }

  @GetMapping("/save")
  public ResponseEntity<?> saveChart() {
    try {
      managerChart.savePicture();
      return ResponseEntity.ok().build();
    } catch (IOException e) {
      return ResponseEntity.status(500).body("Неверный путь или выборка не сформирована");
    }

  }

}
