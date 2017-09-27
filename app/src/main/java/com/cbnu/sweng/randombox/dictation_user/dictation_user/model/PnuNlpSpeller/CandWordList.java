package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller;

import java.io.Serializable;

/**
 * Created by son on 2017-09-23.
 */

public class CandWordList implements Serializable
{
    private int m_nCount;

    private String[] candWord;

    public int getM_nCount ()
    {
        return m_nCount;
    }

    public void setM_nCount (int m_nCount)
    {
        this.m_nCount = m_nCount;
    }

    public String[] getCandWord ()
    {
        return candWord;
    }

    public void setCandWord (String[] CandWord)
    {
        this.candWord = CandWord;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [m_nCount = "+m_nCount+", CandWord = "+candWord+"]";
    }
}
