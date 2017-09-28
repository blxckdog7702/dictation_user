
package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class PnuErrorWord implements Serializable
{

    @SerializedName("Help")
    @Expose
    private Help help;

    @SerializedName("orgStr")
    @Expose
    private String orgStr;

    @SerializedName("m_nStart")
    @Expose
    private int m_nStart;

    @SerializedName("nErrorIdx")
    @Expose
    private int nErrorIdx;

    @SerializedName("candWordList")
    @Expose
    private CandWordList candWordList;

    @SerializedName("m_nEnd")
    @Expose
    private int m_nEnd;

    public Help getHelp ()
    {
        return help;
    }

    public void setHelp (Help help)
    {
        this.help = help;
    }

    public String getOrgStr ()
    {
        return orgStr;
    }

    public void setOrgStr (String orgStr)
    {
        this.orgStr = orgStr;
    }

    public int getM_nStart ()
    {
        return m_nStart;
    }

    public void setM_nStart (int m_nStart)
    {
        this.m_nStart = m_nStart;
    }

    public int getNErrorIdx ()
    {
        return nErrorIdx;
    }

    public void setNErrorIdx (int nErrorIdx)
    {
        this.nErrorIdx = nErrorIdx;
    }

    public CandWordList getCandWordList ()
    {
        return candWordList;
    }

    public void setCandWordList (CandWordList candWordList)
    {
        this.candWordList = candWordList;
    }

    public int getM_nEnd ()
    {
        return m_nEnd;
    }

    public void setM_nEnd (int m_nEnd)
    {
        this.m_nEnd = m_nEnd;
    }

}
