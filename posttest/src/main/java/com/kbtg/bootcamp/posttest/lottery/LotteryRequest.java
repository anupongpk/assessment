package com.kbtg.bootcamp.posttest.lottery;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LotteryRequest(
        @NotNull(message ="Ticket is required.")
        @Size(min = 6, max=6, message ="Ticket must be 6 digits.")
        String ticket,

        @Min(value = 80, message ="Price must be at least 80." )
        int price,

        @Min(value= 1, message = "Amount must be at least 1")
        int amount){ }
