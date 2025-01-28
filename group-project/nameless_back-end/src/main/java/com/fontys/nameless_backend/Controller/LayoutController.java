package com.fontys.nameless_backend.Controller;

import com.fontys.nameless_backend.Business.LayoutService;
import com.fontys.nameless_backend.Controller.Requests.layout.BoxRequest;
import com.fontys.nameless_backend.Controller.Responses.layout.BoxListResponse;
import com.fontys.nameless_backend.Domain.layout.Box;
import com.fontys.nameless_backend.Domain.layout.BoxList;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/layout")
@AllArgsConstructor
public class LayoutController {
    private final LayoutService layoutService;
    private final ModelMapper modelMapper;

    @GetMapping("{id}")
    public ResponseEntity<BoxListResponse> getLayout(@PathVariable Integer id) {
        return ResponseEntity.ok(convertToResponse(layoutService.getLayout(id)));
    }

    @PostMapping
    public ResponseEntity<Integer> saveLayout(@RequestBody @Valid List<BoxRequest> boxListRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(layoutService.saveLayout(convertToDomain(boxListRequest)));
    }

    private BoxList convertToDomain(List<BoxRequest> boxListRequest) {
        List<Box> boxes = boxListRequest.stream()
                .map(request -> modelMapper.map(request, Box.class))
                .collect(Collectors.toList());
        return BoxList.builder().boxes(boxes).build();
    }

    private BoxListResponse convertToResponse(BoxList boxList) {
        return modelMapper.map(boxList, BoxListResponse.class);
    }
}
