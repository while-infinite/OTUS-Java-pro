package otus.study.cashmachine.bank.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PutMoneyDTO {
    private  String number;
    private  String pin;
    private  BigDecimal amount;
}
