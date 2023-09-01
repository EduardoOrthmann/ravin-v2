package com.example.ravin.common_interfaces;

import java.time.LocalDateTime;

public interface Auditable {
    void setCreatedDate(LocalDateTime createdDate);
    void setLastModifiedDate(LocalDateTime lastModifiedDate);
    void setCreatedBy(String createdBy);
    void setLastModifiedBy(String lastModifiedBy);
}
