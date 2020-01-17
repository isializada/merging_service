package de.alizada.merging_service.merging_service.controller;

import de.alizada.merging_service.merging_service.service.MergingService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merge")
public class MergingController {
    private final MergingService mergingService;

    public MergingController(final MergingService mergingService) {
        this.mergingService = mergingService;
    }

    @GetMapping("/{id}")
    public String mergeData(@PathVariable @NonNull Integer id){
        return mergingService.merge(id);
    }
}
