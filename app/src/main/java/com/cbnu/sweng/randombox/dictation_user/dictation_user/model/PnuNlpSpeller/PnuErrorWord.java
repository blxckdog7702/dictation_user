package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller;

import java.io.Serializable;

/**
 * Created by son on 2017-09-23.
 */

public class PnuErrorWord implements Serializable
{
    private Help Help;

    private String orgStr;

    private int m_nStart;

    private int nErrorIdx;

    private CandWordList candWordList;

    private int m_nEnd;

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

    public void setCandWordList (CandWordList CandWordList)
    {
        this.candWordList = CandWordList;
    }

    public int getM_nEnd ()
    {
        return m_nEnd;
    }

    public void setM_nEnd (int m_nEnd)
    {
        this.m_nEnd = m_nEnd;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Help = "+Help+", OrgStr = "+orgStr+", m_nStart = "+m_nStart+", nErrorIdx = "+nErrorIdx+", CandWordList = "+candWordList+", m_nEnd = "+m_nEnd+"]";
    }
}