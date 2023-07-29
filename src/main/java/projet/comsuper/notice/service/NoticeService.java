package projet.comsuper.notice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projet.comsuper.notice.dto.NoticeDTO;
import projet.comsuper.notice.entity.NoticeEntity;
import projet.comsuper.notice.entity.NoticeFileEntity;
import projet.comsuper.notice.repository.NoticeFileRepository;
import projet.comsuper.notice.repository.NoticeRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;
    public void save(NoticeDTO noticeDTO) throws IOException {
        // 파일 첨부 여부에 따라 로직 분리
        if (noticeDTO.getNoticeFile().isEmpty()) {
            // 첨부 파일 없음.
            NoticeEntity noticeEntity = NoticeEntity.toSaveEntity(noticeDTO);
            noticeRepository.save(noticeEntity);
        } else {
            // 첨부 파일 있음.
            /*
                1. DTO에 담긴 파일을 꺼냄
                2. 파일의 이름 가져옴
                3. 서버 저장용 이름을 만듦
                // 내사진.jpg => 839798375892_내사진.jpg
                4. 저장 경로 설정
                5. 해당 경로에 파일 저장
                6. notice_table에 해당 데이터 save 처리
                7. notice_file_table에 해당 데이터 save 처리
             */
            MultipartFile noticeFile = noticeDTO.getNoticeFile(); // 1.
            String originalFilename = noticeFile.getOriginalFilename(); // 2.
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename; // 3.
            String savePath = "C:/springboot_img/" + storedFileName; // 4. C:/springboot_img/9802398403948_내사진.jpg
//            String savePath = "/Users/사용자이름/springboot_img/" + storedFileName; // C:/springboot_img/9802398403948_내사진.jpg
            noticeFile.transferTo(new File(savePath)); // 5.
            NoticeEntity noticeEntity = NoticeEntity.toSaveFileEntity(noticeDTO);
            Long savedId = noticeRepository.save(noticeEntity).getId();
            NoticeEntity notice = noticeRepository.findById(savedId).get();

            NoticeFileEntity noticeFileEntity = NoticeFileEntity.toNoticeFileEntity(notice, originalFilename, storedFileName);
            noticeFileRepository.save(noticeFileEntity);
        }

    }

    /**
     * 공지사항 전체 조회
     * @return
     */
    @Transactional
    public List<NoticeDTO> findAll() {
        List<NoticeEntity> noticeEntityList = noticeRepository.findAll();
        List<NoticeDTO> noticeDTOList = new ArrayList<>();
        for (NoticeEntity noticeEntity: noticeEntityList) {
            noticeDTOList.add(NoticeDTO.toNoticeDTO(noticeEntity));
        }
        return noticeDTOList;
    }

    /**
     *
     * @param id
     * @return
     */
    @Transactional
    public NoticeDTO findById(Long id) {
        Optional<NoticeEntity> optionalNoticeEntity = noticeRepository.findById(id);
        if (optionalNoticeEntity.isPresent()) {
            NoticeEntity noticeEntity = optionalNoticeEntity.get();
            NoticeDTO noticeDTO = NoticeDTO.toNoticeDTO(noticeEntity);
            return noticeDTO;
        } else {
            return null;
        }
    }

    public NoticeDTO update(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = NoticeEntity.toUpdateEntity(noticeDTO);
        noticeRepository.save(noticeEntity);
        return findById(noticeDTO.getId());
    }

    /**
     * 게시판 삭제
     * @param id
     */
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }

    /*public Page<NoticeDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<NoticeEntity> noticeEntities =
                noticeRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("noticeEntities.getContent() = " + noticeEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("noticeEntities.getTotalElements() = " + noticeEntities.getTotalElements()); // 전체 글갯수
        System.out.println("noticeEntities.getNumber() = " + noticeEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("noticeEntities.getTotalPages() = " + noticeEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("noticeEntities.getSize() = " + noticeEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("noticeEntities.hasPrevious() = " + noticeEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("noticeEntities.isFirst() = " + noticeEntities.isFirst()); // 첫 페이지 여부
        System.out.println("noticeEntities.isLast() = " + noticeEntities.isLast()); // 마지막 페이지 여부

        // 목록: id, writerId, writer, title, createdTime
        Page<NoticeDTO> noticeDTOS = noticeEntities.map(board -> new NoticeDTO(noticeDTo.ge, board.getBoardWriterId(), board.getBoardWriter(), board.getBoardTitle(), board.getCreatedTime()));
        return boardDTOS;
    }*/
}