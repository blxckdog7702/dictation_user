
package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PnuNlpSpeller implements Serializable
{
    @SerializedName("pnuErrorWordList")
    @Expose
    private PnuErrorWordList[] pnuErrorWordList;

    public PnuErrorWordList[] getPnuErrorWordList ()
    {
        return pnuErrorWordList;
    }

    public void setPnuErrorWordList (PnuErrorWordList[] PnuErrorWordList)
    {
        this.pnuErrorWordList = PnuErrorWordList;
    }

}
