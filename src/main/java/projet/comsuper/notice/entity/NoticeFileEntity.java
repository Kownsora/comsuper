package projet.comsuper.notice.entity;

import lombok.Getter;
import lombok.Setter;
import projet.comsuper.notice.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "notice_file")
public class NoticeFileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private NoticeEntity noticeEntity;

    public static projet.comsuper.notice.entity.NoticeFileEntity toNoticeFileEntity(NoticeEntity noticeEntity, String originalFileName, String storedFileName) {
        projet.comsuper.notice.entity.NoticeFileEntity noticeFileEntity = new projet.comsuper.notice.entity.NoticeFileEntity();
        noticeFileEntity.setOriginalFileName(originalFileName);
        noticeFileEntity.setStoredFileName(storedFileName);
        noticeFileEntity.setNoticeEntity(noticeEntity);
        return noticeFileEntity;
    }
}
