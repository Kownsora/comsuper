package projet.comsuper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import projet.comsuper.member.MemberRepository;
import projet.comsuper.member.MemberService;
import projet.comsuper.member.MemoryMemberRepository;

@Configuration
public class SpringConfig {
    /**
     * 자바코드로 직접 스프핑 빈 등록
     */
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
