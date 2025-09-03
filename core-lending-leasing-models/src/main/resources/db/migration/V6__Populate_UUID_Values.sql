-- V6 - POPULATE UUID VALUES FOR EXISTING DATA
-- This migration populates the newly added UUID columns with generated values
-- while maintaining referential integrity between tables

-- ========================================================================
-- POPULATE PRIMARY KEY UUIDs (generate new UUIDs for each record)
-- ========================================================================

-- Generate UUIDs for leasing_agreement primary keys
UPDATE leasing_agreement 
SET leasing_agreement_uuid = uuid_generate_v4()
WHERE leasing_agreement_uuid IS NULL;

-- Generate UUIDs for leasing_asset primary keys
UPDATE leasing_asset 
SET leasing_asset_uuid = uuid_generate_v4()
WHERE leasing_asset_uuid IS NULL;

-- Generate UUIDs for lease_installment_schedule primary keys
UPDATE lease_installment_schedule 
SET lease_installment_schedule_uuid = uuid_generate_v4()
WHERE lease_installment_schedule_uuid IS NULL;

-- Generate UUIDs for lease_payment_record primary keys
UPDATE lease_payment_record 
SET lease_payment_record_uuid = uuid_generate_v4()
WHERE lease_payment_record_uuid IS NULL;

-- Generate UUIDs for lease_service_event primary keys
UPDATE lease_service_event 
SET lease_service_event_uuid = uuid_generate_v4()
WHERE lease_service_event_uuid IS NULL;

-- Generate UUIDs for lease_end_option primary keys
UPDATE lease_end_option 
SET lease_end_option_uuid = uuid_generate_v4()
WHERE lease_end_option_uuid IS NULL;

-- ========================================================================
-- POPULATE FOREIGN KEY UUIDs (copy from referenced tables)
-- ========================================================================

-- Update leasing_agreement_uuid in leasing_asset table
UPDATE leasing_asset 
SET leasing_agreement_uuid = la.leasing_agreement_uuid
FROM leasing_agreement la
WHERE leasing_asset.leasing_agreement_id = la.leasing_agreement_id;

-- Update leasing_agreement_uuid in lease_installment_schedule table
UPDATE lease_installment_schedule 
SET leasing_agreement_uuid = la.leasing_agreement_uuid
FROM leasing_agreement la
WHERE lease_installment_schedule.leasing_agreement_id = la.leasing_agreement_id;

-- Update leasing_agreement_uuid in lease_payment_record table
UPDATE lease_payment_record 
SET leasing_agreement_uuid = la.leasing_agreement_uuid
FROM leasing_agreement la
WHERE lease_payment_record.leasing_agreement_id = la.leasing_agreement_id;

-- Update lease_installment_schedule_uuid in lease_payment_record table (optional FK)
UPDATE lease_payment_record 
SET lease_installment_schedule_uuid = lis.lease_installment_schedule_uuid
FROM lease_installment_schedule lis
WHERE lease_payment_record.lease_installment_schedule_id = lis.lease_installment_schedule_id
AND lease_payment_record.lease_installment_schedule_id IS NOT NULL;

-- Update leasing_asset_uuid in lease_service_event table
UPDATE lease_service_event 
SET leasing_asset_uuid = la.leasing_asset_uuid
FROM leasing_asset la
WHERE lease_service_event.leasing_asset_id = la.leasing_asset_id;

-- Update leasing_agreement_uuid in lease_end_option table
UPDATE lease_end_option 
SET leasing_agreement_uuid = la.leasing_agreement_uuid
FROM leasing_agreement la
WHERE lease_end_option.leasing_agreement_id = la.leasing_agreement_id;

-- ========================================================================
-- ADD NOT NULL CONSTRAINTS TO PRIMARY KEY UUID COLUMNS
-- ========================================================================

ALTER TABLE leasing_agreement 
ALTER COLUMN leasing_agreement_uuid SET NOT NULL;

ALTER TABLE leasing_asset 
ALTER COLUMN leasing_asset_uuid SET NOT NULL,
ALTER COLUMN leasing_agreement_uuid SET NOT NULL;

ALTER TABLE lease_installment_schedule 
ALTER COLUMN lease_installment_schedule_uuid SET NOT NULL,
ALTER COLUMN leasing_agreement_uuid SET NOT NULL;

ALTER TABLE lease_payment_record 
ALTER COLUMN lease_payment_record_uuid SET NOT NULL,
ALTER COLUMN leasing_agreement_uuid SET NOT NULL;

ALTER TABLE lease_service_event 
ALTER COLUMN lease_service_event_uuid SET NOT NULL,
ALTER COLUMN leasing_asset_uuid SET NOT NULL;

ALTER TABLE lease_end_option 
ALTER COLUMN lease_end_option_uuid SET NOT NULL,
ALTER COLUMN leasing_agreement_uuid SET NOT NULL;