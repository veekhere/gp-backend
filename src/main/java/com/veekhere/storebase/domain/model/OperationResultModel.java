package com.veekhere.storebase.domain.model;

import com.veekhere.storebase.domain.enums.OperationStatus;

public class OperationResultModel {
    public record OperationResult(OperationStatus status) {}
}
