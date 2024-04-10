package com.wiinvent.gami.domain.service.user.transaction;

import com.wiinvent.gami.domain.entities.transaction.PointTransaction;
import com.wiinvent.gami.domain.response.TransactionResponse;
import com.wiinvent.gami.domain.response.base.PageCursorResponse;
import com.wiinvent.gami.domain.response.type.CursorType;
import com.wiinvent.gami.domain.service.BaseService;
import com.wiinvent.gami.domain.utils.Helper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class PointTransactionService extends BaseService {
  public PageCursorResponse<TransactionResponse> getPointTransaction(UUID userId, Long next, Long pre, int limit) {
    CursorType type = CursorType.FIRST;
    List<PointTransaction> pointTransactions = new ArrayList<>();
    if (next == null && pre == null) {
      next = Helper.getNowMillisAtUtc();
      pre = 0L;
      pointTransactions = pointTransactionStorage.findAll(userId, next, pre, limit, type);
    } else if (pre == null){
      type = CursorType.NEXT;
      pre = 0L;
      pointTransactions = pointTransactionStorage.findAll(userId, next, pre, limit, type);
    } else if (next == null){
      type = CursorType.PRE;
      next = Helper.getNowMillisAtUtc();
      pointTransactions = pointTransactionStorage.findAll(userId, next, pre, limit, type);
      pointTransactions = pointTransactions.stream().sorted(Comparator.comparingLong(PointTransaction::getCreatedAt).reversed()).toList();
    }
    List<TransactionResponse> responses = modelMapper.toPointTransactionResponse(pointTransactions);
    return new PageCursorResponse<>(responses, limit, next, pre, "created");
  }
}
