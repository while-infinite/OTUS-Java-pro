package otus.study.cashmachine.bank.dto;

import lombok.Data;

@Data
public class PinDTO {
    private  String number;
    private  String oldPin;
    private  String newPin;
}
