-- V1 - CREATE ENUMS FOR FINANCE LEASING SUBMODULE

-- leasing_agreement -> agreement_status
CREATE TYPE agreement_status AS ENUM (
    'ACTIVE',
    'RESTRUCTURED',
    'CLOSED',
    'DEFAULTED',
    'TERMINATED'
);

-- leasing_asset -> asset_type
CREATE TYPE asset_type AS ENUM (
    'VEHICLE',
    'EQUIPMENT',
    'REAL_ESTATE',
    'MACHINERY',
    'TECH_DEVICE'
);

-- lease_service_event -> event_type
CREATE TYPE event_type AS ENUM (
    'MAINTENANCE',
    'REPAIR',
    'INSPECTION',
    'ACCIDENT',
    'WARRANTY',
    'UPGRADE'
);