-- V3 - CASTS USING "WITH INOUT AS IMPLICIT" FOR LEASING ENUMS

-------------------------
-- agreement_status
-------------------------
CREATE CAST (varchar AS agreement_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- asset_type
-------------------------
CREATE CAST (varchar AS asset_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- event_type
-------------------------
CREATE CAST (varchar AS event_type)
    WITH INOUT
    AS IMPLICIT;
