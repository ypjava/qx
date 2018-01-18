package xyz.yp.module.qx.common.wrapper;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rongdu.cashloan.core.common.util.StringUtil;

/**
 * Created by lsk on 2016/7/29.
 */
@SuppressWarnings({ "rawtypes", "unused" })
public abstract class AppLoginedActionWraper extends AppAbsActionWrapper {
    public AppLoginedActionWraper(HttpServletResponse _resp, HttpServletRequest _req) {
        super(_resp, _req);
    }


    private String getSignParam(String name) {
        String value = _req.getParameter(name);
        if (StringUtils.isEmpty(value)) {
            value = _req.getHeader(name);
        }
        return value;
    }

    @Override
    public Object doAction()  {
    	if(_req.getSession()==null){
    		logger.error("session为空");
    		return null;
    	}
        Map userData = (Map) _req.getSession().getAttribute("userData");
        if(userData!=null)
        	userData= (Map) userData.get("userData");
        String userId = (String)_req.getSession().getAttribute("userId");
        if(userData!=null&&userId!=null)
        	return doAction(userData, userId);
        else
        	return null;
//        try {
//			userData= (Map) userData.get("userData");
//		} catch (Exception e) {
//			logger.error("session为空");
//			logger.error(e.getMessage(),e);
//		}
        
    }


    public abstract Object doAction(Map userData, String userId) ;

}
