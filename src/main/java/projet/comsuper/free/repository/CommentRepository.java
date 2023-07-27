package projet.comsuper.free.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.comsuper.free.entity.BoardEntity;
import projet.comsuper.free.entity.CommentEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // select * from comment where board_id=? order by id desc;
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}
