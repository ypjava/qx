package xyz.yp.module.qx.common.wrapper;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractBaseActionWrapper {
    protected String dataRoot;
    protected HttpServletRequest _req;
    protected HttpServletResponse _resp;
    protected boolean writeJson = true;

    public AbstractBaseActionWrapper(HttpServletResponse response) {
        this(response, null);
    }

    public AbstractBaseActionWrapper(HttpServletResponse response, HttpServletRequest request) {
        this._resp = _resp;
        this._req = _req;

        /*Object result = null;
        try {
            result = doAction();

            if (result instanceof Map) {
                Map _result = (Map) result;

                boolean isSuccess = (Boolean) _result.get("success");
                _result.put("result", isSuccess ? 1 : 400);
                _result.put("message", _result.get(Constant.RESPONSE_CODE_MSG));

                Object data = ((Map) result).get(Constant.RESPONSE_DATA);
                if (data instanceof Map) {
                    MapUtil.replaceNullValue2EmptyStr((Map) data);
                }

                _result.remove("success");
                _result.remove(Constant.RESPONSE_CODE_MSG);
            }

        } catch (Exception e) {
            logger.warn("{}", e);
            result = MapUtil.array2Map(new Object[][] {
                {"result", 500},
                {"message", "系统异常，请稍后再试，或联系系统管理员"}
            });
        }
        if (writeJson) {
            JsonUtil.writeJson(result, _resp);
        }*/
    }

    public static Map getParams(HttpServletRequest request) {
        LinkedHashMap params = new LinkedHashMap();
        Enumeration enames = request.getParameterNames();

        while (enames.hasMoreElements()) {
            String name = (String) enames.nextElement();
            params.put(toUnderLine(name), request.getParameter(name));
        }
        return params;
    }


    public static String toUnderLine(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c >= 65 && c <= 90) {
                c += 32;
                sb.append("_");
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public abstract Object doAction() ;
}
