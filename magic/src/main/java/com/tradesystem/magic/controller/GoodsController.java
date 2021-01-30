package com.tradesystem.magic.controller;

import com.tradesystem.magic.domain.Good;
import com.tradesystem.magic.domain.projection.NamesDescriptions;
import com.tradesystem.magic.dto.GoodRequest;
import com.tradesystem.magic.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = GoodsController.PATH, produces = "application/json")
public class GoodsController {

    static final String PATH = "/good";
    private final GoodService goodService;

    @GetMapping
    public ResponseEntity<List<Good>> getGoods() {
        return ResponseEntity.ok(goodService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Good> getGoodById(@PathVariable Long id) throws NotFoundException {
        var foundGood = goodService.findById(id);
        return ResponseEntity.ok(foundGood);
    }

    @GetMapping("/{id}/description")
    public ResponseEntity<NamesDescriptions> getNamesDescriptionsById(@PathVariable Long id) {
        var nameDescription = goodService.findNameDescriptionById(id);
        return ResponseEntity.ok(nameDescription);
    }

    @PostMapping
    public ResponseEntity<Void> createGood(@Valid @RequestBody GoodRequest request,
            UriComponentsBuilder uriComponentsBuilder) {
        var createdGoodId = goodService.createGood(request);
        var uriComponents = uriComponentsBuilder.path(PATH + "/{id}").buildAndExpand(createdGoodId);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Good> updateGood(@Valid @RequestBody GoodRequest request, @PathVariable Long id,
            UriComponentsBuilder uriComponentsBuilder) {
        var updatedGood = goodService.updateGood(id, request);
        return ResponseEntity.ok(updatedGood);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGood(Long id) {
        goodService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
