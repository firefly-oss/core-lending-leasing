-- V5 - ADD UUID COLUMNS FOR LONG TO UUID MIGRATION
-- This migration adds UUID columns alongside existing BIGINT columns
-- to prepare for the Long to UUID refactoring

-- ========================================================================
-- ADD UUID EXTENSION IF NOT EXISTS
-- ========================================================================
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ========================================================================
-- TABLE: leasing_agreement - Add UUID columns
-- ========================================================================
ALTER TABLE leasing_agreement 
ADD COLUMN leasing_agreement_uuid UUID,
ADD COLUMN contract_uuid UUID,
ADD COLUMN customer_uuid UUID;

-- ========================================================================
-- TABLE: leasing_asset - Add UUID columns
-- ========================================================================
ALTER TABLE leasing_asset 
ADD COLUMN leasing_asset_uuid UUID,
ADD COLUMN leasing_agreement_uuid UUID,
ADD COLUMN asset_type_uuid UUID;

-- ========================================================================
-- TABLE: lease_installment_schedule - Add UUID columns
-- ========================================================================
ALTER TABLE lease_installment_schedule 
ADD COLUMN lease_installment_schedule_uuid UUID,
ADD COLUMN leasing_agreement_uuid UUID;

-- ========================================================================
-- TABLE: lease_payment_record - Add UUID columns
-- ========================================================================
ALTER TABLE lease_payment_record 
ADD COLUMN lease_payment_record_uuid UUID,
ADD COLUMN leasing_agreement_uuid UUID,
ADD COLUMN lease_installment_schedule_uuid UUID,
ADD COLUMN transaction_uuid UUID;

-- ========================================================================
-- TABLE: lease_service_event - Add UUID columns
-- ========================================================================
ALTER TABLE lease_service_event 
ADD COLUMN lease_service_event_uuid UUID,
ADD COLUMN leasing_asset_uuid UUID;

-- ========================================================================
-- TABLE: lease_end_option - Add UUID columns
-- ========================================================================
ALTER TABLE lease_end_option 
ADD COLUMN lease_end_option_uuid UUID,
ADD COLUMN leasing_agreement_uuid UUID;