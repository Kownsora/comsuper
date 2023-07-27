package projet.comsuper.notice.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import projet.comsuper.free.entity.BoardEntity;
import projet.comsuper.notice.entity.NoticeEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor  // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class NoticeDTO {
    private Long id;
    private String writerId;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private MultipartFile boardFile; // save.html -> Controller 파일 담는 용도
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // 서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)

    public NoticeDTO(Long id, String writerId, String writer, String title, LocalDateTime createdTime) {
        this.id = id;
        this.writerId = writerId;
        this.writer = writer;
        this.title = title;
        this.createdTime = createdTime;
    }

    public static projet.comsuper.notice.dto.NoticeDTO toNoticeDTO(NoticeEntity noticeEntity) {
        projet.comsuper.notice.dto.NoticeDTO noticeDTO = new projet.comsuper.notice.dto.NoticeDTO();
        noticeDTO.setId(noticeDTO.getId());
        noticeDTO.setWriterId(noticeDTO.getWriterId());
        noticeDTO.setWriter(noticeDTO.getWriter());
        noticeDTO.setTitle(noticeDTO.getTitle());
        noticeDTO.setContents(noticeDTO.getContents());
        noticeDTO.setCreatedTime(noticeDTO.getCreatedTime());
        noticeDTO.setUpdatedTime(noticeDTO.getUpdatedTime());
        if (noticeEntity.getFileAttached() == 0) {
            noticeDTO.setFileAttached(noticeEntity.getFileAttached()); // 0
        } else {
            noticeDTO.setFileAttached(noticeEntity.getFileAttached()); // 1
            // 파일 이름을 가져가야 함.
            // orginalFileName, storedFileName : board_file_table(BoardFileEntity)
            // join
            // select * from board_table b, board_file_table bf where b.id=bf.board_id
            // and where b.id=?
            noticeDTO.setOriginalFileName(noticeEntity.getFileEntityList().get(0).getOriginalFileName());
            noticeDTO.setStoredFileName(noticeEntity.getFileEntityList().get(0).getStoredFileName());
        }

        return noticeDTO;
    }
}