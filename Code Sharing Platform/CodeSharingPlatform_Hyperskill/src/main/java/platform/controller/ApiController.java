package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.code_service.Code;
import platform.code_service.CodeService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {


    @Autowired
    CodeService codeService;

    @GetMapping("/code/{id}")
    public Code getCodeByIdWeb(@PathVariable("id") String id) {
        return codeService.getCodeById(id);
    }

    @GetMapping("/code/latest")
    public List<Code> getLatestCodeWeb() {
        return codeService.getLatestCodes();
    }

    @PostMapping("/code/new")
    public Map<String, String> addNewCodeWeb(@RequestBody Code code) {
        return Map.of("id",codeService.addCode(code));
    }
}
