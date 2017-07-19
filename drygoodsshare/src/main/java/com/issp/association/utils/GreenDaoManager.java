package com.issp.association.utils;

import com.issp.association.App;
import com.issp.association.greendao.gen.DaoMaster;
import com.issp.association.greendao.gen.DaoSession;

/**
 * Created by T-BayMax on 2017/7/14.
 */

public class GreenDaoManager {
        private static GreenDaoManager mInstance;
        private DaoMaster mDaoMaster;
        private DaoSession mDaoSession;


        private GreenDaoManager() {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getContext(), "notes-db", null);
            DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }

        public static GreenDaoManager getInstance() {
            if (mInstance == null) {
                mInstance = new GreenDaoManager();
            }
            return mInstance;
        }

        public DaoMaster getMaster() {
            return mDaoMaster;
        }

        public DaoSession getSession() {
            return mDaoSession;
        }

        public DaoSession getNewSession() {
            mDaoSession = mDaoMaster.newSession();
            return mDaoSession;
        }
}
