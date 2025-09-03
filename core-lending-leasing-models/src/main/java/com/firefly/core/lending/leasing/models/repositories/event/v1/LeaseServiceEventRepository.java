package com.firefly.core.lending.leasing.models.repositories.event.v1;

import com.firefly.core.lending.leasing.models.entities.event.v1.LeaseServiceEvent;
import com.firefly.core.lending.leasing.models.repositories.BaseRepository;

import java.util.UUID;

public interface LeaseServiceEventRepository extends BaseRepository<LeaseServiceEvent, UUID> {
}
