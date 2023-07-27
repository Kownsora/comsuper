package projet.comsuper.notice.entity;

import lombok.Getter;
import lombok.Setter;
import projet.comsuper.notice.dto.NoticeDTO;
import projet.comsuper.notice.entity.BaseEntity;
import projet.comsuper.notice.entity.NoticeFileEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "notice")
public class NoticeEntity extends BaseEntity {
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String writerId;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String writer;

    @Column
    private String title;

    @Column(length = 500)
    private String contents;

    @Column
    private int fileAttached; // 1 or 0

    @OneToMany(mappedBy = "noticeEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<NoticeFileEntity> fileEntityList = new ArrayList<>();

    public static projet.comsuper.notice.entity.NoticeEntity toSaveEntity(NoticeDTO noticeDTO) {
        projet.comsuper.notice.entity.NoticeEntity noticeEntity = new projet.comsuper.notice.entity.NoticeEntity();
        noticeEntity.setWriterId(noticeDTO.getWriterId());
        noticeEntity.setWriter(noticeDTO.getWriter());
        noticeEntity.setTitle(noticeDTO.getTitle());
        noticeEntity.setContents(noticeDTO.getContents());
        noticeEntity.setFileAttached(0); // 파일 없음.
        return noticeEntity;
    }

    public static projet.comsuper.notice.entity.NoticeEntity toUpdateEntity(NoticeDTO noticeDTO) {
        projet.comsuper.notice.entity.NoticeEntity noticeEntity = new projet.comsuper.notice.entity.NoticeEntity();
        noticeEntity.setId(noticeDTO.getId());
        noticeEntity.setWriterId(noticeDTO.getWriterId());
        noticeEntity.setWriter(noticeDTO.getWriter());
        noticeEntity.setTitle(noticeDTO.getTitle());
        noticeEntity.setContents(noticeDTO.getContents());
        return noticeEntity;
    }

    public static projet.comsuper.notice.entity.NoticeEntity toSaveFileEntity(NoticeDTO noticeDTO) {
        projet.comsuper.notice.entity.NoticeEntity noticeEntity = new projet.comsuper.notice.entity.NoticeEntity();
        noticeEntity.setWriterId(noticeDTO.getWriterId());
        noticeEntity.setWriter(noticeDTO.getWriter());
        noticeEntity.setTitle(noticeDTO.getTitle());
        noticeEntity.setContents(noticeDTO.getContents());
        noticeEntity.setFileAttached(1); // 파일 있음.
        return noticeEntity;
    }
}
