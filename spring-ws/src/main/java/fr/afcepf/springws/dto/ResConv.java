package fr.afcepf.springws.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class ResConv {
    private String src; //ex: "EUR"
    private String target;//ex: "USD"
    private Double amount; //ex: 200.0
    private Double result;//ex: 210.0
}
