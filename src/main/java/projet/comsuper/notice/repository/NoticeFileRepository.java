package projet.comsuper.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.comsuper.notice.entity.NoticeFileEntity;

public interface NoticeFileRepository extends JpaRepository<NoticeFileEntity, Long> {
}
