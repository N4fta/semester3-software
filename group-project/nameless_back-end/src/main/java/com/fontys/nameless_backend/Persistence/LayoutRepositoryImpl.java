package com.fontys.nameless_backend.Persistence;

import com.fontys.nameless_backend.Business.LayoutRepository;
import com.fontys.nameless_backend.Business.exceptions.LayoutDoesNotExistException;
import com.fontys.nameless_backend.Domain.layout.BoxList;
import com.fontys.nameless_backend.Persistence.entities.layout.BoxListEntity;
import com.fontys.nameless_backend.Persistence.mongoDb.BoxListRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class LayoutRepositoryImpl implements LayoutRepository {
    private final BoxListRepository boxListRepository;
    private final ModelMapper modelMapper;

    @Override
    public Integer saveLayout(BoxList boxList) {
        BoxListEntity latestBox = boxListRepository.findTopByOrderByIdDesc();
        int layoutId = (latestBox != null ? latestBox.getId() : 0) + 1;
        boxList.setId(layoutId);
        return boxListRepository.save(convertToEntity(boxList)).getId();
    }

    @Override
    public BoxList getLayout(Integer id) {
        return boxListRepository.findById(id)
                .map(this::convertToDomain)
                .orElseThrow(LayoutDoesNotExistException::new);
    }

    private BoxListEntity convertToEntity(BoxList boxList) {
        return modelMapper.map(boxList, BoxListEntity.class);
    }

    private BoxList convertToDomain(BoxListEntity boxListEntity) {
        return modelMapper.map(boxListEntity, BoxList.class);
    }
}
