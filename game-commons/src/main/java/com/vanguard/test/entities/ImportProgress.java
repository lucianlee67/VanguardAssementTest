package com.vanguard.test.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = "import_progress")
@ToString
@Schema(title = " Import Progress Entity")
public class ImportProgress {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "status")
    private String status; // Examples: "IN_PROGRESS", "COMPLETED", "FAILED"

    @Column(name = "total_records")
    private int totalRecords;

    @Column(name = "processed_records")
    private int processedRecords;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
