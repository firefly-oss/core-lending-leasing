-- V2 - CREATE TABLES FOR THE FINANCE LEASING SUBMODULE

-- ========================================================================
-- TABLE: leasing_agreement
-- ========================================================================
CREATE TABLE IF NOT EXISTS leasing_agreement (
                                                 leasing_agreement_id   BIGSERIAL PRIMARY KEY,
                                                 contract_id            BIGINT,  -- external reference (Contract domain)
                                                 customer_id            BIGINT,  -- external reference (Customer domain)
                                                 agreement_status       agreement_status NOT NULL, -- e.g. ACTIVE, DEFAULTED
                                                 start_date             DATE NOT NULL,
                                                 end_date               DATE,  -- possibly extended or might adjust
                                                 principal_amount       DECIMAL(18,2) NOT NULL, -- financed amount
    interest_rate          DECIMAL(9,4) NOT NULL,
    residual_value         DECIMAL(18,2) DEFAULT 0,
    purchase_option        BOOLEAN DEFAULT TRUE, -- if there's a buyout option
    remarks                TEXT,
    created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- ========================================================================
-- TABLE: leasing_asset
-- ========================================================================
CREATE TABLE IF NOT EXISTS leasing_asset (
                                             leasing_asset_id       BIGSERIAL PRIMARY KEY,
                                             leasing_agreement_id   BIGINT NOT NULL,
                                             asset_type             asset_type NOT NULL,
                                             asset_description      VARCHAR(255) NOT NULL,
    asset_serial_number    VARCHAR(100),
    asset_value            DECIMAL(18,2),
    is_active             BOOLEAN DEFAULT TRUE,
    note                   TEXT,
    created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_asset_agreement
    FOREIGN KEY (leasing_agreement_id)
    REFERENCES leasing_agreement (leasing_agreement_id)
    );

-- ========================================================================
-- TABLE: lease_installment_schedule
-- ========================================================================
CREATE TABLE IF NOT EXISTS lease_installment_schedule (
                                                          lease_installment_schedule_id BIGSERIAL PRIMARY KEY,
                                                          leasing_agreement_id         BIGINT NOT NULL,
                                                          installment_number           INT NOT NULL,
                                                          due_date                     DATE NOT NULL,
                                                          principal_due                DECIMAL(18,2) NOT NULL,
    interest_due                 DECIMAL(18,2) NOT NULL,
    fee_due                      DECIMAL(18,2) DEFAULT 0,
    total_due                    DECIMAL(18,2) NOT NULL,
    is_paid                      BOOLEAN DEFAULT FALSE,
    paid_date                    DATE,
    note                         TEXT,
    created_at                   TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                   TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_installment_agreement
    FOREIGN KEY (leasing_agreement_id)
    REFERENCES leasing_agreement (leasing_agreement_id)
    );

-- ========================================================================
-- TABLE: lease_payment_record
-- ========================================================================
CREATE TABLE IF NOT EXISTS lease_payment_record (
                                                    lease_payment_record_id      BIGSERIAL PRIMARY KEY,
                                                    leasing_agreement_id         BIGINT NOT NULL,
                                                    lease_installment_schedule_id BIGINT, -- optional link if it matches a schedule
                                                    transaction_id               BIGINT,  -- external Payment domain reference
                                                    payment_amount               DECIMAL(18,2) NOT NULL,
    payment_date                 DATE NOT NULL,
    is_partial_payment           BOOLEAN DEFAULT FALSE,
    note                         TEXT,
    created_at                   TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                   TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_payment_agreement
    FOREIGN KEY (leasing_agreement_id)
    REFERENCES leasing_agreement (leasing_agreement_id)
    -- Optionally add a FK to lease_installment_schedule if you want a direct link
    );

-- ========================================================================
-- TABLE: lease_service_event
-- ========================================================================
CREATE TABLE IF NOT EXISTS lease_service_event (
                                                   lease_service_event_id  BIGSERIAL PRIMARY KEY,
                                                   leasing_asset_id        BIGINT NOT NULL,
                                                   event_date              DATE NOT NULL,
                                                   event_type              event_type NOT NULL,
                                                   cost                    DECIMAL(18,2) DEFAULT 0,
    note                    TEXT,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_service_asset
    FOREIGN KEY (leasing_asset_id)
    REFERENCES leasing_asset (leasing_asset_id)
    );

-- ========================================================================
-- TABLE: lease_end_option
-- ========================================================================
CREATE TABLE IF NOT EXISTS lease_end_option (
                                                lease_end_option_id     BIGSERIAL PRIMARY KEY,
                                                leasing_agreement_id    BIGINT NOT NULL,
                                                option_exercise_date    DATE, -- if the buyout was done early or on maturity
                                                option_paid_amount      DECIMAL(18,2) DEFAULT 0,
    is_exercised            BOOLEAN DEFAULT FALSE,
    note                    TEXT,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_end_option_agreement
    FOREIGN KEY (leasing_agreement_id)
    REFERENCES leasing_agreement (leasing_agreement_id)
    );
