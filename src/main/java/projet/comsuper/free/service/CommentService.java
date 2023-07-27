package projet.comsuper.free.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projet.comsuper.free.dto.CommentDTO;
import projet.comsuper.free.entity.BoardEntity;
import projet.comsuper.free.entity.CommentEntity;
import projet.comsuper.free.repository.BoardRepository;
import projet.comsuper.free.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    /**
     * 댓글 저장
     * @param commentDTO
     * @return
     */
    public Long save(CommentDTO commentDTO) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());  //부모글(게시글) 조회
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }
    }

    /**
     * 목록 조회
     * @param boardId
     * @return
     */
    public List<CommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);

        //EntityList -> DTOList
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}