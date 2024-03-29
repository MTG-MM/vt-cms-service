package com.wiinvent.gami.app.controller.reward;

import com.wiinvent.gami.domain.dto.RewardTypeDto;
import com.wiinvent.gami.domain.dto.RewardTypeUpdateDto;
import com.wiinvent.gami.domain.response.RewardTypeResponse;
import com.wiinvent.gami.domain.service.reward.RewardTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vt/cms/reward-types")
@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
public class RewardTypeController {

  @Autowired
  private RewardTypeService rewardTypeService;

  @GetMapping("")
  public ResponseEntity<List<RewardTypeResponse>> getRewardTypes() {
    return ResponseEntity.ok(rewardTypeService.getAllRewardTypes());
  }

  @GetMapping("{id}")
  public ResponseEntity<RewardTypeResponse> getRewardTypeDetail(@PathVariable Long id) {
    return ResponseEntity.ok(rewardTypeService.getRewardTypeDetail(id));
  }

  @PostMapping("")
  public ResponseEntity<Long> createRewardTypes(@RequestBody RewardTypeDto rewardTypeDto) {
    return ResponseEntity.ok(rewardTypeService.createRewardTypes(rewardTypeDto));
  }

  @PutMapping("{id}")
  public ResponseEntity<Boolean> updateRewardTypes(@PathVariable Long id, @RequestBody @Valid RewardTypeUpdateDto rewardTypeDto) {
    return ResponseEntity.ok(rewardTypeService.updateRewardTypes(id, rewardTypeDto));
  }
}
