package com.gookkis.realmtutorial;


import android.app.Application;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .schemaVersion(0)
                .migration(new DataMigration())
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private class DataMigration implements RealmMigration{
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            //mengambil schema
            RealmSchema schema = realm.getSchema();

            //membuat shema baru jika versi 0

            if (oldVersion == 0){
                schema.create("Article")
                        .addField("id", int.class)
                        .addField("title", String.class)
                        .addField("desciption",String.class);
                oldVersion++;
            }
        }
    }
}