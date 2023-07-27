package projet.comsuper.free.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projet.comsuper.free.dto.BoardDTO;
import projet.comsuper.free.dto.CommentDTO;
import projet.comsuper.free.service.BoardService;
import projet.comsuper.free.service.CommentService;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    /**
     * 게시판 입력 화면
     * @return
     */
    @GetMapping("/save")
    public String saveForm() {
        return "free/free-write";
    }

    /**
     * 게시판 입력
     * @param boardDTO
     * @return
     * @throws IOException
     */
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "free/free-list";
    }

    /**
     * 게시판 목록 조회
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "free/free-list";
    }

    /**
     * 게시판 상세 조회
     * @param id
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable) {
        //boardService.updateHits(id);  //조회수 업데이트
        BoardDTO boardDTO = boardService.findById(id);
        //List<CommentDTO> commentDTOList = commentService.findAll(id);  //댓글 목록 조회
        //model.addAttribute("commentList", commentDTOList);  //댓글 목록 HTML로 넘길 변수
        model.addAttribute("board", boardDTO);
        //model.addAttribute("page", pageable.getPageNumber());
        return "free/free-list";
    }

    /**
     * 게시판 수정화면
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "free/free-list";
    }

    /**
     * 게시판 수정
     * @param boardDTO
     * @param model
     * @return
     */
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "free/free-list";
//        return "redirect:/board/" + boardDTO.getId();
    }

    /**
     * 게시판 삭제
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/list";
    }

    // /board/paging?page=1
    /*@GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        //pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        // page 갯수 20개
        // 현재 사용자가 3페이지
        // 1 2 3
        // 현재 사용자가 7페이지
        // 7 8 9
        // 보여지는 페이지 갯수 3개
        // 총 페이지 갯수 8개

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";
    }*/

}
