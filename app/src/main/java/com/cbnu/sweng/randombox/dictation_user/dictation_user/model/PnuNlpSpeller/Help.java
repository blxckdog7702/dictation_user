
package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Help
{

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("nCorrectMethod")
    @Expose
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

}
