package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller;

/**
 * Created by son on 2017-09-23.
 */

public class PnuErrorWord
{
    private Help Help;

    private String orgStr;

    private String m_nStart;

    private String nErrorIdx;

    private CandWordList candWordList;

    private String m_nEnd;

    public Help getHelp ()
    {
        return Help;
    }

    public void setHelp (Help Help)
    {
        this.Help = Help;
    }

    public String getOrgStr ()
    {
        return orgStr;
    }

    public void setOrgStr (String OrgStr)
    {
        this.orgStr = OrgStr;
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

    public void setCandWordList (CandWordList CandWordList)
    {
        this.candWordList = CandWordList;
    }

    public String getM_nEnd ()
    {
        return m_nEnd;
    }

    public void setM_nEnd (String m_nEnd)
    {
        this.m_nEnd = m_nEnd;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Help = "+Help+", OrgStr = "+orgStr+", m_nStart = "+m_nStart+", nErrorIdx = "+nErrorIdx+", CandWordList = "+candWordList+", m_nEnd = "+m_nEnd+"]";
    }
}