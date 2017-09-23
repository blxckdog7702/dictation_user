package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller;

/**
 * Created by son on 2017-09-23.
 */

public class PnuErrorWordList
{
    private String repeat;

    private PnuErrorWord[] pnuErrorWord;

    private Error error;

    public String getRepeat ()
    {
        return repeat;
    }

    public void setRepeat (String repeat)
    {
        this.repeat = repeat;
    }

    public PnuErrorWord[] getPnuErrorWord ()
    {
        return pnuErrorWord;
    }

    public void setPnuErrorWord (PnuErrorWord[] PnuErrorWord)
    {
        this.pnuErrorWord = PnuErrorWord;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [repeat = "+repeat+", PnuErrorWord = "+pnuErrorWord+"]";
    }


}