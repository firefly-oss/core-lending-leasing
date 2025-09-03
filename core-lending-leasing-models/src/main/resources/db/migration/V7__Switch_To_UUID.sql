-- V7 - SWITCH TO UUID COLUMNS AND DROP BIGINT COLUMNS
-- This migration completes the transition from BIGINT to UUID by:
-- 1. Dropping old foreign key constraints
-- 2. Dropping old BIGINT columns
-- 3. Renaming UUID columns to final names
-- 4. Creating new foreign key constraints with UUID columns
-- 5. Creating indexes on UUID columns

-- ========================================================================
-- DROP OLD FOREIGN KEY CONSTRAINTS
-- ========================================================================

-- Drop foreign key constraints that reference BIGINT columns
ALTER TABLE leasing_asset DROP CONSTRAINT IF EXISTS fk_asset_agreement;
ALTER TABLE lease_installment_schedule DROP CONSTRAINT IF EXISTS fk_installment_agreement;
ALTER TABLE lease_payment_record DROP CONSTRAINT IF EXISTS fk_payment_agreement;
ALTER TABLE lease_service_event DROP CONSTRAINT IF EXISTS fk_service_asset;
ALTER TABLE lease_end_option DROP CONSTRAINT IF EXISTS fk_end_option_agreement;

-- ========================================================================
-- DROP OLD BIGINT COLUMNS
-- ========================================================================

-- Drop BIGINT primary and foreign key columns
ALTER TABLE leasing_agreement 
DROP COLUMN leasing_agreement_id,
DROP COLUMN contract_id,
DROP COLUMN customer_id;

ALTER TABLE leasing_asset 
DROP COLUMN leasing_asset_id,
DROP COLUMN leasing_agreement_id,
DROP COLUMN asset_type_id;

ALTER TABLE lease_installment_schedule 
DROP COLUMN lease_installment_schedule_id,
DROP COLUMN leasing_agreement_id;

ALTER TABLE lease_payment_record 
DROP COLUMN lease_payment_record_id,
DROP COLUMN leasing_agreement_id,
DROP COLUMN lease_installment_schedule_id,
DROP COLUMN transaction_id;

ALTER TABLE lease_service_event 
DROP COLUMN lease_service_event_id,
DROP COLUMN leasing_asset_id;

ALTER TABLE lease_end_option 
DROP COLUMN lease_end_option_id,
DROP COLUMN leasing_agreement_id;

-- ========================================================================
-- RENAME UUID COLUMNS TO FINAL NAMES
-- ========================================================================

-- Rename UUID columns to match original column names (without _uuid suffix)
ALTER TABLE leasing_agreement 
RENAME COLUMN leasing_agreement_uuid TO leasing_agreement_id;

ALTER TABLE leasing_agreement 
RENAME COLUMN contract_uuid TO contract_id;

ALTER TABLE leasing_agreement 
RENAME COLUMN customer_uuid TO customer_id;

ALTER TABLE leasing_asset 
RENAME COLUMN leasing_asset_uuid TO leasing_asset_id;

ALTER TABLE leasing_asset 
RENAME COLUMN leasing_agreement_uuid TO leasing_agreement_id;

ALTER TABLE leasing_asset 
RENAME COLUMN asset_type_uuid TO asset_type_id;

ALTER TABLE lease_installment_schedule 
RENAME COLUMN lease_installment_schedule_uuid TO lease_installment_schedule_id;

ALTER TABLE lease_installment_schedule 
RENAME COLUMN leasing_agreement_uuid TO leasing_agreement_id;

ALTER TABLE lease_payment_record 
RENAME COLUMN lease_payment_record_uuid TO lease_payment_record_id;

ALTER TABLE lease_payment_record 
RENAME COLUMN leasing_agreement_uuid TO leasing_agreement_id;

ALTER TABLE lease_payment_record 
RENAME COLUMN lease_installment_schedule_uuid TO lease_installment_schedule_id;

ALTER TABLE lease_payment_record 
RENAME COLUMN transaction_uuid TO transaction_id;

ALTER TABLE lease_service_event 
RENAME COLUMN lease_service_event_uuid TO lease_service_event_id;

ALTER TABLE lease_service_event 
RENAME COLUMN leasing_asset_uuid TO leasing_asset_id;

ALTER TABLE lease_end_option 
RENAME COLUMN lease_end_option_uuid TO lease_end_option_id;

ALTER TABLE lease_end_option 
RENAME COLUMN leasing_agreement_uuid TO leasing_agreement_id;

-- ========================================================================
-- ADD PRIMARY KEY CONSTRAINTS ON UUID COLUMNS
-- ========================================================================

ALTER TABLE leasing_agreement ADD PRIMARY KEY (leasing_agreement_id);
ALTER TABLE leasing_asset ADD PRIMARY KEY (leasing_asset_id);
ALTER TABLE lease_installment_schedule ADD PRIMARY KEY (lease_installment_schedule_id);
ALTER TABLE lease_payment_record ADD PRIMARY KEY (lease_payment_record_id);
ALTER TABLE lease_service_event ADD PRIMARY KEY (lease_service_event_id);
ALTER TABLE lease_end_option ADD PRIMARY KEY (lease_end_option_id);

-- ========================================================================
-- CREATE NEW FOREIGN KEY CONSTRAINTS WITH UUID COLUMNS
-- ========================================================================

-- Foreign key constraints with UUID columns
ALTER TABLE leasing_asset 
ADD CONSTRAINT fk_asset_agreement 
FOREIGN KEY (leasing_agreement_id) 
REFERENCES leasing_agreement (leasing_agreement_id);

ALTER TABLE lease_installment_schedule 
ADD CONSTRAINT fk_installment_agreement 
FOREIGN KEY (leasing_agreement_id) 
REFERENCES leasing_agreement (leasing_agreement_id);

ALTER TABLE lease_payment_record 
ADD CONSTRAINT fk_payment_agreement 
FOREIGN KEY (leasing_agreement_id) 
REFERENCES leasing_agreement (leasing_agreement_id);

ALTER TABLE lease_payment_record 
ADD CONSTRAINT fk_payment_schedule 
FOREIGN KEY (lease_installment_schedule_id) 
REFERENCES lease_installment_schedule (lease_installment_schedule_id);

ALTER TABLE lease_service_event 
ADD CONSTRAINT fk_service_asset 
FOREIGN KEY (leasing_asset_id) 
REFERENCES leasing_asset (leasing_asset_id);

ALTER TABLE lease_end_option 
ADD CONSTRAINT fk_end_option_agreement 
FOREIGN KEY (leasing_agreement_id) 
REFERENCES leasing_agreement (leasing_agreement_id);

-- ========================================================================
-- CREATE INDEXES ON UUID FOREIGN KEY COLUMNS FOR PERFORMANCE
-- ========================================================================

CREATE INDEX idx_leasing_asset_agreement_id ON leasing_asset (leasing_agreement_id);
CREATE INDEX idx_lease_installment_schedule_agreement_id ON lease_installment_schedule (leasing_agreement_id);
CREATE INDEX idx_lease_payment_record_agreement_id ON lease_payment_record (leasing_agreement_id);
CREATE INDEX idx_lease_payment_record_schedule_id ON lease_payment_record (lease_installment_schedule_id);
CREATE INDEX idx_lease_service_event_asset_id ON lease_service_event (leasing_asset_id);
CREATE INDEX idx_lease_end_option_agreement_id ON lease_end_option (leasing_agreement_id);