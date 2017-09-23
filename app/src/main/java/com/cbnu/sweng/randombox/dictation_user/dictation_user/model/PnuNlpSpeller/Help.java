package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller;

/**
 * Created by son on 2017-09-23.
 */

public class Help
{
    private String text;

    private int nCorrectMethod;

    public String getText ()
    {
        return text;
    }

    public void setText (String content)
    {
        this.text = content;
    }

    public int getNCorrectMethod ()
    {
        return nCorrectMethod;
    }

    public void setNCorrectMethod (int nCorrectMethod)
    {
        this.nCorrectMethod = nCorrectMethod;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+text+", nCorrectMethod = "+nCorrectMethod+"]";
    }
}