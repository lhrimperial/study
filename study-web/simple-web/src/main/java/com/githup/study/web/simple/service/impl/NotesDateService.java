package com.githup.study.web.simple.service.impl;

import com.githup.study.web.simple.domain.NotesDate;
import com.githup.study.web.simple.mapper.NotesDateMapper;
import com.githup.study.web.simple.service.INotesDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author longhairen
 * @create 2017/10/11 0011 上午 10:31
 */
//@Service
public class NotesDateService implements INotesDateService {

    @Autowired
    private NotesDateMapper notesDateMapper;

    @Override
    public void save(NotesDate notesDate) {
        notesDateMapper.save(notesDate);
    }

    @Override
    public NotesDate findById(int id) {
        return notesDateMapper.findById(id);
    }
}
