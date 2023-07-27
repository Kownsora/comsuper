package projet.comsuper.member.dto;

import lombok.*;
import projet.comsuper.member.entity.MemberEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String password;
    private String name;
    private String email;

    /**
     * MemberEntity -> MemberDTO로 변환
     * @param memberEntity
     * @return
     */
    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setPassword(memberEntity.getPassword());
        memberDTO.setName(memberEntity.getName());
        return memberDTO;
    }
}
