package projet.comsuper.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.comsuper.member.entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 이메일로 회원 정보 조회 (select * from member where email=?)
    Optional<MemberEntity> findByEmail(String email);
}
