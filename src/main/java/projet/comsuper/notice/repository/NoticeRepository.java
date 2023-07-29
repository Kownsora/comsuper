package projet.comsuper.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.comsuper.notice.entity.NoticeEntity;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
}
