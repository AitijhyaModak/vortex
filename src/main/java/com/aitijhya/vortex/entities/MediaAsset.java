package com.aitijhya.vortex.entities;

import com.aitijhya.vortex.enums.VideoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String UUID;

    @NotNull
    private String originalFileName;

    @NotNull
    private String systemFileName;

    private String thumbnailName;

    @Enumerated(EnumType.STRING)
    private VideoStatus status = VideoStatus.PENDING;

    private String aiGeneratedSummary;

    private LocalDateTime createdAt = LocalDateTime.now();

}
