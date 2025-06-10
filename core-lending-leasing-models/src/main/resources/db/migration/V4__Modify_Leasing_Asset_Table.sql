-- V4 - MODIFY LEASING_ASSET TABLE

-- Add new asset_type_id column
ALTER TABLE leasing_asset
    ADD COLUMN asset_type_id BIGINT;

-- Remove the asset_type column
ALTER TABLE leasing_asset
DROP COLUMN asset_type;