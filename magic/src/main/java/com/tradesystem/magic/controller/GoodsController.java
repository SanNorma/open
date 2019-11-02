package com.tradesystem.magic.controller;

import java.util.List;
import javax.validation.Valid;

import com.tradesystem.magic.domain.Good;
import com.tradesystem.magic.dto.GoodRequest;
import com.tradesystem.magic.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping(GoodsController.PATH)
public class GoodsController {

    static final String PATH = "/good";
    private final GoodService goodService;

    @GetMapping
    public ResponseEntity<List<Good>> getGoods() {
        return ResponseEntity.ok(goodService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Good> getGoodById(@PathVariable Long id) throws NotFoundException {
        Good foundGood = goodService.findById(id);
        return ResponseEntity.ok(foundGood);
    }

    @PostMapping
    public ResponseEntity<Void> createGood(@Valid @RequestBody GoodRequest request,
            UriComponentsBuilder uriComponentsBuilder) {
        Long createdGoodId = goodService.createGood(request);
        UriComponents uriComponents = uriComponentsBuilder.path(PATH + "/{id}").buildAndExpand(createdGoodId);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Good> updateGood(@Valid @RequestBody GoodRequest request, @PathVariable Long id,
            UriComponentsBuilder uriComponentsBuilder) {
        Good updatedGood = goodService.updateGood(id, request);
        return ResponseEntity.ok(updatedGood);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGood(Long id) {
        goodService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
