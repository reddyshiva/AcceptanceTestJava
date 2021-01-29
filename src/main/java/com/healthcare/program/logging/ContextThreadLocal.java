package com.healthcare.program.logging;
import java.util.Map;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import com.healthcare.program.constants.Constants;

public class ContextThreadLocal
{
    private static ThreadLocal<HealthCareContext> ctxLocal = new ThreadLocal<HealthCareContext>();


    public static void set(HealthCareContext context)
    {
    	ctxLocal.set(context);
        addContextToMDC(context);
    }

    public static HealthCareContext get()
    {
        return ctxLocal.get();
    }


    public static void clear()
    {
    	ctxLocal.set(null);
    	removeContextFromMDC();
    }


    public static void addContextToMDC(HealthCareContext context)
    {
    	if (null != context) {
    		if (context.getHealthCareTid() != null)
    			addToMDC(Constants.HEALTHCARE_TID, context.getHealthCareTid());
    		if (context.getUserId() != null)
    			addToMDC(Constants.HEALTHCARE_USERID, context.getUserId());
    		if(context.getDependentUserId() != null)
    			addToMDC(Constants.HEALTHCARE_DEPENDENTUSERID, context.getDependentUserId());


       	}
    }


    public static void addToMDC(String localName, String value)
    {
    	MDC.put(localName, value);
        MDC.put(localName + Constants.FORMAT_KEY, " " + localName + "=" + value + " |");
    }

    public static String getFromMDC(String key)
    {
    	if(StringUtils.isEmpty(key)) {
    		return null;
    	}
    	Map<String,String> mdcContext =  MDC.getCopyOfContextMap();
    	if (mdcContext != null) {
    		return mdcContext.get(key);
    		
    	}
    	return null;
    }


    public static void removeContextFromMDC()
    {
    	Map<String,String> mdcContext =  MDC.getCopyOfContextMap();
    	if (mdcContext != null) {
			if (mdcContext.get(Constants.HEALTHCARE_TID)!=null){
				removeFromMDC(Constants.HEALTHCARE_TID);
			}
			if (mdcContext.get(Constants.HEALTHCARE_USERID)!=null){
				removeFromMDC(Constants.HEALTHCARE_USERID);
			}
			
			if (mdcContext.get(Constants.HEALTHCARE_DEPENDENTUSERID)!=null){
				removeFromMDC(Constants.HEALTHCARE_DEPENDENTUSERID);
			}
    		
    	}
    }


    public static void removeFromMDC(String localName)
    {
    	if (MDC.get(localName) != null)
    		MDC.remove(localName);
    	if (MDC.get(localName + Constants.FORMAT_KEY) != null)
    		MDC.remove(localName + Constants.FORMAT_KEY);
    }
}
