package com.example.pawar.fastrescue.manager;

import android.content.Context;

import com.example.pawar.fastrescue.dao.PhotoCollectionDao;
import com.example.pawar.fastrescue.dao.StatusCollectionDao;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class StatusListManager {

    private Context mContext;
    private static StatusCollectionDao dao;

    public StatusListManager() {
        mContext = Contextor.getInstance().getContext();

    }

    public static StatusCollectionDao getDao() {
        return dao;
    }

    public void setDao(StatusCollectionDao dao) {
        this.dao = dao;
    }


}
