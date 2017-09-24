package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by son on 2017-09-23.
 */

public class PnuNlpSpeller implements Serializable {
    private PnuErrorWordList[] pnuErrorWordList;

    public PnuErrorWordList[] getPnuErrorWordList ()
    {
        return pnuErrorWordList;
    }

    public void setPnuErrorWordList (PnuErrorWordList[] PnuErrorWordList)
    {
        this.pnuErrorWordList = PnuErrorWordList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [PnuErrorWordList = "+pnuErrorWordList+"]";
    }
}
