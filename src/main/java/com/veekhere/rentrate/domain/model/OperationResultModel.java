package com.veekhere.rentrate.domain.model;

import com.veekhere.rentrate.domain.enums.OperationStatus;

public class OperationResultModel {
    public record OperationResult(OperationStatus status) {}
}
