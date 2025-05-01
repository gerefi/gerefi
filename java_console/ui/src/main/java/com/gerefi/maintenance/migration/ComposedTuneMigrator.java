package com.gerefi.maintenance.migration;

import java.util.Arrays;
import java.util.List;

import com.gerefi.maintenance.DefaultTuneMigrator;

public enum ComposedTuneMigrator implements TuneMigrator {
    INSTANCE;

    private final List<TuneMigrator> tuneMigrators = Arrays.asList(
        VeTableExtensionMigrator.INSTANCE,
        BattLagCorrExtensionMigrator.INSTANCE,
        CltIdleCorrMigrator.INSTANCE,
        DefaultTuneMigrator.INSTANCE
    );

    @Override
    public void migrateTune(final TuneMigrationContext context) {
        for (final TuneMigrator tuneMigrator: tuneMigrators) {
            tuneMigrator.migrateTune(context);
        }
    }
}
