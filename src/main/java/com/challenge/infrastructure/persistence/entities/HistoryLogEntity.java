package com.challenge.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "history_log")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String endpoint;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String parameters;

    @Column(columnDefinition = "TEXT")
    private Double result;

    @PrePersist
    public void prePersist() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
    }
}
