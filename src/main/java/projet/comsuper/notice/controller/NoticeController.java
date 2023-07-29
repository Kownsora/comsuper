package projet.comsuper.notice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projet.comsuper.notice.dto.NoticeDTO;
import projet.comsuper.notice.service.NoticeService;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

    /**
     * 공지사항 입력 화면
     * @return
     */
    @GetMapping("/save")
    public String saveForm() {
        return "notice/notice-edit";
    }

    /**
     * 공지사항 입력
     * @param noticeDTO
     * @return
     * @throws IOException
     */
    @PostMapping("/save")
    public String save(@ModelAttribute NoticeDTO noticeDTO) throws IOException {
        System.out.println("noticeDTO = " + noticeDTO);
        noticeService.save(noticeDTO);
        return "notice/notice-list";
    }

    /**
     * 공지사항 목록 조회
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String findAll(Model model) {
        List<NoticeDTO> noticeDTOList = noticeService.findAll();
        model.addAttribute("noticeList", noticeDTOList);
        return "notice/notice-list";
    }

    /**
     * 공지사항 상세 조회
     * @param id
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable) {
        //noticeService.updateHits(id);  //조회수 업데이트
        NoticeDTO noticeDTO = noticeService.findById(id);
        model.addAttribute("notice", noticeDTO);
        //model.addAttribute("page", pageable.getPageNumber());
        return "notice/notice-list";
    }

    /**
     * 공지사항 수정화면
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        NoticeDTO noticeDTO = noticeService.findById(id);
        model.addAttribute("noticeUpdate", noticeDTO);
        return "notice/notice-list";
    }

    /**
     * 공지사항 수정
     * @param noticeDTO
     * @param model
     * @return
     */
    @PostMapping("/update")
    public String update(@ModelAttribute NoticeDTO noticeDTO, Model model) {
        NoticeDTO notice = noticeService.update(noticeDTO);
        model.addAttribute("notice", notice);
        return "notice/notice-list";
//        return "redirect:/notice/" + noticeDTO.getId();
    }

    /**
     * 공지사항 삭제
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        noticeService.delete(id);
        return "redirect:/notice/list";
    }
}
