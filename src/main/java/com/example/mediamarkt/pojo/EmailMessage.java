package com.example.mediamarkt.pojo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmailMessage implements Serializable {

    private String to;
    private String subject;
    private String text;


}
