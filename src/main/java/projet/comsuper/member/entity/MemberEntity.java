package projet.comsuper.member.entity;


import lombok.Getter;
import lombok.Setter;
import projet.comsuper.member.dto.MemberDTO;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="member")
public class MemberEntity {
    @Id //pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(unique = true) //unique 제약조건 추가
    private String email;

    @Column
    private String password;

    @Column(unique = true) //unique 제약조건 추가
    private String name;

    /**
     * MemberDTO -> MemberEntity로 변환
     * @param memberDTO
     * @return
     */
    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setName(memberDTO.getName());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setName(memberDTO.getName());
        return memberEntity;
    }
}
