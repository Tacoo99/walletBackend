package com.pai2_pro.wallet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Min(1)
    @NotNull(message = "Kwota nie może być pusta")
    private Double amount;
    private String description;
    @Min(1)
    @Max(3)
    private int type;//1 for Income, 2 for expense
    @JsonFormat(pattern = "yyyy-mm-dd")
    private String transactionDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id", nullable = false)
    @JsonIgnore
    private Wallet wallet;

    @PrePersist
    public void setTransactionDate() {
        Date currentDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.transactionDate = ft.format(currentDate);
    }
}