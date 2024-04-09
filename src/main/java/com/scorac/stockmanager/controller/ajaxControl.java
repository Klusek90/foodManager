package com.scorac.stockmanager.controller;

//import com.scorac.stockmanager.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;

        import java.util.HashMap;
        import java.util.Map;

@Controller
public class ajaxControl {
    @GetMapping("/greenboxbooking")
    public ResponseEntity<Map<String, String>> getTestValues() {
        Map<String, String> response = new HashMap<>();
        response.put("booking", "20");
        response.put("notes", "Here are some notes passed by chefs about preparation etc.");
        return ResponseEntity.ok(response);
    }

}
