package projet.comsuper.free.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projet.comsuper.free.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
