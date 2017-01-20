package com.nv.netmdkb.security.Interceptor;

import java.util.ArrayList;
import java.util.List;



import java.util.regex.Pattern;

import com.nv.framework.util.collection.CollectionUtil;
import com.nv.framework.util.StringUtil;



public class RegexUtil{
   
    public static String toRegexFriendlyString(String input)
    {
        if (StringUtil.isEmpty(input))
        {
            return "";
        }

        StringBuffer pattern = new StringBuffer();
        int inputLength = input.length();
        for (int i = 0; i < inputLength; i++)
        {
            char ch = input.charAt(i);
            if (!Character.isLetterOrDigit(ch))
            {
                pattern.append("\\");
            }
            pattern.append(ch);
        }

        return pattern.toString();
    }

    public static List<Pattern> toRegexPatterns(List<String> regexList)
    {
        if (CollectionUtil.isEmpty(regexList))
        {
            return null;
        }

        List<Pattern> patternList = new ArrayList<Pattern>(regexList.size());
       
        for(String regex:regexList){
            if (StringUtil.isBlank(regex))
            {
                continue;
            }
           
                patternList.add(Pattern.compile(regex));
            
          
        }
       
        return patternList;
    }

    public static boolean match(List<Pattern> regexList, String text)
    {
        if (CollectionUtil.isEmpty(regexList))
        {
            return false;
        }
        if (StringUtil.isBlank(text))
        {
            return false;
        }

       
        for (  Pattern regex:regexList)
        {
             if (regex.matcher(text) != null)
            {
                return true;
            }
        }

        return false;
    }

   
}