package com.cal;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExpressionGenerate
{
    private int maxNum = 10;
    private Random random = new Random();
    public static String[] signList = {"+","-","×","÷"};
    protected String getNaturalNum(int max)
    {
        return String.valueOf(random.nextInt(max+1));
    }
    protected String getSign()
    {
        int signNum = random.nextInt(4);
        return signList[signNum];
    }
    protected String getProperFraction()
    {
        int numerator = Integer.parseInt(getNaturalNum(maxNum));
        int denominator = Integer.parseInt(getNaturalNum(maxNum));
        while (numerator>=denominator)
        {
            if(numerator==10)
            {
                denominator = 10;
                break;
            }
            denominator = Integer.parseInt(getNaturalNum(maxNum));
        }

        return numerator+"/"+denominator;
    }
    protected String getNum()
    {

        boolean isNaturalNum = random.nextBoolean();
        return isNaturalNum?getNaturalNum(maxNum):getProperFraction();
    }

    private String createNumOnly()
    {
        return getSign()+" "+getNum()+" ";
    }
    private String createSubExpression()
    {
        String sign = getSign();
        String num1Str = getNaturalNum(maxNum);
        String num2Str = getNaturalNum(maxNum);
        return getSign()+" ( "+num1Str +" "+ sign + " " +num2Str+" ) ";
    }
    public String createExpression(int signNum)
    {

        StringBuilder result= new StringBuilder();
        result.append(getNum()).append(" ");
        while(signNum>0)
        {
            boolean isCreateSubExpression = random.nextBoolean();
            if(signNum>=2 && isCreateSubExpression)
            {
                result.append(createSubExpression());
                signNum-=2;
            }
            else
            {
                result.append(createNumOnly());
                signNum--;
            }
        }
        return result.toString();
    }

}
