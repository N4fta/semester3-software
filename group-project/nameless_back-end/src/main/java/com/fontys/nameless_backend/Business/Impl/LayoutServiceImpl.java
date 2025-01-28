package com.fontys.nameless_backend.Business.Impl;

import com.fontys.nameless_backend.Business.LayoutRepository;
import com.fontys.nameless_backend.Business.LayoutService;
import com.fontys.nameless_backend.Domain.layout.BoxList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LayoutServiceImpl implements LayoutService {
    private final LayoutRepository layoutRepository;

    @Override
    public Integer saveLayout(BoxList boxList) {
        return layoutRepository.saveLayout(boxList);
    }

    @Override
    public BoxList getLayout(Integer id) {
        return layoutRepository.getLayout(id);
    }
}
