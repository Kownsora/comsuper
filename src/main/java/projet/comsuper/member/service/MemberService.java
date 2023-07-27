package projet.comsuper.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projet.comsuper.member.dto.MemberDTO;
import projet.comsuper.member.entity.MemberEntity;
import projet.comsuper.member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param memberDTO
     */
    public void save(MemberDTO memberDTO) {
        //1. DTO -> entity 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        //2. repository의 save 메서드 호출
        memberRepository.save(memberEntity);

        //repository의 save 메서드 호출(조건:entity 객체를 넘겨줘야 함)
    }

    /**
     * 로그인
     * @param memberDTO
     * @return
     */
    public MemberDTO login(MemberDTO memberDTO) {
        //1. 회원이 입력한 이메일로 DB에서 조회함
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getEmail());

        if(byMemberEmail.isPresent()) {
            //해당 이메일을 가진 회원 정보가 있다
            MemberEntity memberEntity = byMemberEmail.get();

            //2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
            if(memberEntity.getPassword().equals(memberDTO.getPassword())) {
                //비밀번호가 일치
                //entity -> dto로 변환
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                //비밀번호가 불일치
                return null;
            }

        } else {
            //해당 이메일을 가진 회원 정보가 없다
            return null;
        }
    }

    /**
     * 회원 목록 조회
     * @return
     */
    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();

        for(MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
            //MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            //memberDTOList.add(memberDTO);
        }

        return memberDTOList;
    }

    /**
     * 회원 상세 조회
     * @param id
     * @return
     */
    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    /**
     * 회원 수정 화면
     * @param memberEmail
     * @return
     */
    public MemberDTO updateForm(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if(optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    /**
     * 회원 수정
     * @param memberDTO
     */
    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    /**
     * 회원 삭제
     * @param id
     */
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
