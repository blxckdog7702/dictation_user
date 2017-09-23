
package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PnuErrorWord
{

    @SerializedName("Help")
    @Expose
    private Help help;
    @SerializedName("orgStr")
    @Expose
    private String orgStr;
    @SerializedName("m_nStart")
    @Expose
    private String m_nStart;
    @SerializedName("nErrorIdx")
    @Expose
    private String nErrorIdx;
    @SerializedName("candWordList")
    @Expose
    private CandWordList candWordList;
    @SerializedName("m_nEnd")
    @Expose
    private String m_nEnd;

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

    public String getM_nStart ()
    {
        return m_nStart;
    }

    public void setM_nStart (String m_nStart)
    {
        this.m_nStart = m_nStart;
    }

    public String getNErrorIdx ()
    {
        return nErrorIdx;
    }

    public void setNErrorIdx (String nErrorIdx)
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

    public String getM_nEnd ()
    {
        return m_nEnd;
    }

    public void setM_nEnd (String m_nEnd)
    {
        this.m_nEnd = m_nEnd;
    }

}
