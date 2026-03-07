CREATE TABLE VEHICLE (
         ID                             UUID NOT NULL PRIMARY KEY,
         CREATION_TIMESTAMP             TIMESTAMP,
         MODIFICATION_TIMESTAMP         TIMESTAMP,
         USER_ID                        UUID NOT NULL,
         LICENSE_PLATE                  VARCHAR(12),
         MAKE                           VARCHAR(256),
         MODEL                          VARCHAR(256),
         YEAR_OF_CONSTRUCTION           DATE,
         POWER                          SMALLINT,
         GASOLINE_TYPE                  VARCHAR(15),
         PHOTO                          VARCHAR(256),
         PRIMARY_VEHICLE                BOOLEAN
);

CREATE TABLE LOGBOOK_ENTRY (
         ID                             UUID NOT NULL PRIMARY KEY,
         CREATION_TIMESTAMP             TIMESTAMP,
         MODIFICATION_TIMESTAMP         TIMESTAMP,
         VEHICLE_ID                     UUID NOT NULL,
         START_TIME                     TIMESTAMP WITH TIME ZONE,
         END_TIME                       TIMESTAMP WITH TIME ZONE,
         KM_START                       INTEGER,
         KM_END                         INTEGER,
         TRIP                           NUMERIC(20, 2),
         ROUTE                          TEXT,
         COMMENT                        TEXT,
         FOREIGN KEY (VEHICLE_ID) REFERENCES VEHICLE(ID)
);

CREATE TABLE LOGBOOK_FUEL_ENTRY (
        ID                             UUID NOT NULL PRIMARY KEY,
        CREATION_TIMESTAMP             TIMESTAMP,
        MODIFICATION_TIMESTAMP         TIMESTAMP,
        VEHICLE_ID                     UUID NOT NULL,
        DATE                           TIMESTAMP WITH TIME ZONE,
        MILEAGE_STATUS                 SMALLINT,
        TRIP                           NUMERIC(20, 2),
        LITERS                         NUMERIC(20, 2),
        PRICE                          NUMERIC(20, 2),
        COMMENT                        TEXT,
        FOREIGN KEY (VEHICLE_ID) REFERENCES VEHICLE(ID)
);