package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.code_service.Code;
import platform.code_service.CodeService;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    CodeService codeService;

    @GetMapping("/code/{id}")
    public String getCodeByIdWeb(@PathVariable("id") String uuid, Model model) {
        model.addAttribute("codes",List.of(codeService.getCodeById(uuid)));
        model.addAttribute("title","Code");
        return "get_code";
    }

    @GetMapping("/code/latest")
    public String getLatestCodeWeb(Model model) {
        List<Code> codes = codeService.getLatestCodes();
        model.addAttribute("codes",codes);
        model.addAttribute("title","Latest");
        return "get_code";
    }

    @GetMapping("/code/new")
    public String addNewCodeWeb() {
        return "new_code";
    }
}
