package projet.comsuper.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projet.comsuper.member.dto.MemberDTO;
import projet.comsuper.member.service.MemberService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    //생성자 주입
    private final MemberService memberService;

    /**
     * 회원가입 페이지 출력 요청
     * @return
     */
    @GetMapping("/member/save")
    public String saveForm() {
        return "/member/member-write";
    }

    /**
     * 회원가입
     * @param memberDTO
     * @return
     */
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("멤버 저장시 넘어오는 파라미터 확인 : " + memberDTO);
        memberService.save(memberDTO);
        return "main/main";
    }

    /**
     * 로그인 페이지 출력 요청
     * @return
     */
    //TODO : 로그인 화면 구현
    @GetMapping("/member/login")
    public String loginForm() {
        return "/member/login";
    }

    /**
     * 로그인
     * @param memberDTO
     * @return
     */
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        System.out.println("로그인시 넘어오는 파라미터 확인 : " + memberDTO);
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null) {
            //login 성공
            session.setAttribute("loginEmail", loginResult.getEmail());
            return "free/free-list";
        } else {
            //login 실패
            return "member/login";
        }
    }

    /**
     * 회원 목록 조회
     * @return
     */
    @GetMapping("/member/list")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList); //화면으로 값을 넘겨줄때 model 사용
        return "member/member-list";
    }

    /**
     * 회원 상세 조회
     * @param id
     * @return
     */
    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "member/member-detail";
    }

    /**
     * 회원 수정 화면
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String memberEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(memberEmail);
        model.addAttribute("updateMember", memberDTO);
        return "member/member-update";
    }

    /**
     * 회원 수정 실행
     * @param memberDTO
     * @return
     */
    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId();  //회원상세화면 호출 /member/{id}
    }

    /**
     * 회원 삭제 실행
     * @param id
     * @return
     */
    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/member/member-list"; //회원목록화면 호출
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "main/main";
    }
}
