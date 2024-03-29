package com.wiinvent.gami.domain.dto;

import com.wiinvent.gami.domain.entities.type.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GamePackageUpdateDto {

  @NotNull(message = "Không được để trống")
  @Size(max = 50)
  @NotEmpty
  @NotBlank(message = "Không được để trống")
  String code;

  @NotNull
  Integer point;

  @NotNull
  Integer coin;

  @Size(max = 1000)
  String imageUrl;

  @Size(max = 1000)
  String thumbUrl;

  @Size(max = 1000)
  String description;

  Status status;

  Integer priority;

  List<GamePackagePaymentMethodDto> paymentMethods = new ArrayList<>();
}