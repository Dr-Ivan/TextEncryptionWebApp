package com.webchipherapp.demo.DataTransferObjects;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CipherRequestDTO {
    private String text;
    private String key;
}
