package com.firefly.core.lending.leasing.models.entities.event.v1;

import com.firefly.core.lending.leasing.interfaces.enums.event.v1.EventTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("lease_service_event")
public class LeaseServiceEvent {

    @Id
    @Column("lease_service_event_id")
    private Long leaseServiceEventId;

    @Column("leasing_asset_id")
    private Long leasingAssetId; // references LeasingAsset

    @Column("event_date")
    private LocalDate eventDate;

    @Column("event_type")
    private EventTypeEnum eventType;

    @Column("cost")
    private BigDecimal cost;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
