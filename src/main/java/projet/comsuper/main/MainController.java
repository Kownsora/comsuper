package projet.comsuper.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    /**
     * 메인페이지 요청
     * @return
     */
    @GetMapping("/")
    public String Main() {
        return "main/main";  //templates 폴더의 main/main.html 찾아감
    }
}
