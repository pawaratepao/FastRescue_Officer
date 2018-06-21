package com.example.pawar.fastrescue.manager;

import android.content.Context;

import com.example.pawar.fastrescue.dao.EmerCollectionDao;
import com.example.pawar.fastrescue.dao.StatusCollectionDao;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class EmerListManager {

    private Context mContext;
    private static EmerCollectionDao dao;

    public EmerListManager() {
        mContext = Contextor.getInstance().getContext();

    }

    public static EmerCollectionDao getDao() {
        return dao;
    }

    public void setDao(EmerCollectionDao dao) {
        this.dao = dao;
    }


}
