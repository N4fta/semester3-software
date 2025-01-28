package com.fontys.nameless_backend.Business;

import com.fontys.nameless_backend.Domain.layout.BoxList;

public interface LayoutRepository {
    Integer saveLayout(BoxList boxList);
    BoxList getLayout(Integer id);
}
