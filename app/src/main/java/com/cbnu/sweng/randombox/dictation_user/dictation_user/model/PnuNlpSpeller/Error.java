package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller;

/**
 * Created by son on 2017-09-23.
 */

public class Error
{
    private String msg;

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [msg = "+msg+"]";
    }
}
