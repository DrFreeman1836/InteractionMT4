package main.rest;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import main.service.impl.TickManagerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mt")
@RequiredArgsConstructor
public class ControllerMt {

  private final TickManagerServiceImpl tickManagerService;

  @PostMapping()//http://localhost:8080/api/v1/mt?price=0.9935&flag=true
  public ResponseEntity<?> addTick(
      @RequestParam(name = "price") BigDecimal price,
      @RequestParam(name = "flag", required = false, defaultValue = "false") Boolean flag) {

    System.out.println(price);

      Long time = System.currentTimeMillis();
      try {
          tickManagerService.processingTick(price, time, flag);
          return ResponseEntity.ok().build();
      } catch (Exception ex) {
          ex.printStackTrace();
          return ResponseEntity.status(500).build();
      }

  }

}
