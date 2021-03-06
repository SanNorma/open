package com.tradesystem.magic.domain;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
@Data
public class AuditEntity {

    @JsonIgnore
    @CreatedBy
    @Lazy
    private String createdBy;
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdDate;
    @JsonIgnore
    @LastModifiedBy
    @Lazy
    private String lastModifiedBy;
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
