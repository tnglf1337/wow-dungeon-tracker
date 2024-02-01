package com.example.wowdungeontracker.adapter.web.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewCharForm(@NotEmpty(message = "You have not selected a valid name. (Not empty)")
                          @Size(max = 12, message = "Too many literals. (max. 12")
                          String charName) {
}
