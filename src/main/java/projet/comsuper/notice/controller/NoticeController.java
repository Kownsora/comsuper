package projet.comsuper.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {

    @GetMapping("/noticewrite")
    public String noticeWrite() {
        return "notice/notice-write";
    }
}
