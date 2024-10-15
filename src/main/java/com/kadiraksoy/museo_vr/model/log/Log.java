package com.kadiraksoy.museo_vr.model.log;

import com.kadiraksoy.museo_vr.model.enums.LogLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
@Getter
@Setter
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @Enumerated(EnumType.STRING)
    private LogLevel level;
    private LocalDateTime timestamp;

    public Log(String message, LogLevel level) {
        this.message = message;
        this.level = level;
        this.timestamp = LocalDateTime.now();
    }
    public Log() {}
}