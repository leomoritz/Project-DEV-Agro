package com.senai.devagro.devagro.controller.converter;

import com.senai.devagro.devagro.utils.UtilLocalDateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class FarmWithdrawalStockConverter {

    @NotNull(message = "Quantity (kg) is required.")
    private Double quantityKgWithdrawal;

    @NotNull(message = "Withdrawal Date is required.")
    @Pattern(message = "Withdrawal Date format is invalid. Enter the withdrawal date in format dd-mm-yyyy", regexp = "\\d{2}-\\d{2}-\\d{4}")
    private String withdrawalDate;

    public FarmWithdrawalStockConverter(Double quantityKgWithdrawal, String withdrawalDate) {
        this.quantityKgWithdrawal = quantityKgWithdrawal;
        this.withdrawalDate = withdrawalDate;
    }

}
