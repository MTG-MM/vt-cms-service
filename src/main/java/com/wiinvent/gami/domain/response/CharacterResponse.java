package com.wiinvent.gami.domain.response;

import com.wiinvent.gami.domain.entities.type.CharacterCategoryType;
import com.wiinvent.gami.domain.entities.type.Status;
import lombok.Data;

@Data
public class CharacterResponse {
  private Integer id;
  private String name;
  private CharacterCategoryType categoryType;
  private String type;
  private Status status;
  private Integer coinPrice;
  private Integer pointPrice;
  private Long createdAt;
}