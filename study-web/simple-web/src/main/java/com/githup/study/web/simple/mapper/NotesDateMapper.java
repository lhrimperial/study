package com.githup.study.web.simple.mapper;

import com.githup.study.web.simple.domain.NotesDate;
import org.springframework.stereotype.Repository;

/**
 * @author longhairen
 * @create 2017/10/11 0011 上午 10:26
 */
@Repository
public interface NotesDateMapper {

    void save(NotesDate notesDate);

    NotesDate findById(int id);
}
