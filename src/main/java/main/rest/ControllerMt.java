package main.rest;

import lombok.RequiredArgsConstructor;
import main.service.impl.TickManagerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mt")
@RequiredArgsConstructor
public class ControllerMt {

  private final TickManagerServiceImpl tickManagerService;

  public ResponseEntity<?> addTick(
      @RequestParam(name = "price") Double price,
      @RequestParam(name = "time") Long time,
      @RequestParam(name = "flag", required = false, defaultValue = "false") Boolean flag) {

      int result = tickManagerService.processingTick(price, time, flag);
      if(result > 0){
        return ResponseEntity.ok().build();
      } else {
        return ResponseEntity.status(500).build();
      }

  }

}
